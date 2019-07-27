/*******************************************************************************
 * Copyright (C) 2018 fortiss GmbH
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     kross - initial implementation
 ******************************************************************************/
package org.fortiss.pmwt.pertract.dsl.extractor.spark;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.fortiss.pmwt.pertract.dsl.extractor.spark.application.SparkDAO;
import org.fortiss.pmwt.pertract.dsl.extractor.spark.application.stage.StageAttemptDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.spark.application.stage.task.TaskSummaryDTO;
import org.fortiss.pmwt.pertract.dsl.model.application.ApplicationDSLFactory;
import org.fortiss.pmwt.pertract.dsl.model.application.ProcessingType;
import org.fortiss.pmwt.pertract.dsl.model.application.ResourceDemand;
import org.fortiss.pmwt.pertract.dsl.model.application.ResourceProfile;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceProfileExtraction {

	private ApplicationDSLFactory factory;
	private SparkDAO sparkDAO;
	private CassandraResourceDemandsClient agentClient;
	private String parametricDataVariableName = "dataSize.VALUE";
	private String parametricExecutorVariableName = "executors.BYTESIZE";
	private final int bytes_to_megabytes = 1024*1024;
	private long cpuTimePerTask;
	private TaskSummaryDTO taskSummary;
	private final Logger log = LoggerFactory.getLogger(ResourceProfileExtraction.class);
	
	public ResourceProfileExtraction(SparkDAO sparkDAO) {
		this.factory = ApplicationDSLFactory.eINSTANCE;
		this.sparkDAO = sparkDAO;
		this.agentClient = new CassandraResourceDemandsClient();
	}
	
	public ResourceProfile extractResourceProfile(String applicationID, StageAttemptDTO stageAttempt) {
		log.info("Extract resource profile for stage " + stageAttempt.getStageId() + " of app " + applicationID);
		this.cpuTimePerTask = 0;
		
		DateTime experimentStartTime = sparkDAO.getApplication(applicationID).getAttempts().get(0).getStartTime().withZoneRetainFields(DateTimeZone.UTC).withZone(DateTimeZone.getDefault());
		String experiment = new SimpleDateFormat("yyyyMMdd-HH:mm").format(experimentStartTime.toDate());
		log.info("experiment name: " + experiment);
		this.taskSummary = sparkDAO.getTaskSummaryOfStageAttempt(applicationID, stageAttempt.getStageId(), stageAttempt.getAttemptId());
		
		ResourceProfile resourceProfile = factory.createResourceProfile();
		if (stageAttempt.getNumCompleteTasks()!=1) { // no parametric resource demands for stages with only one task
			ResourceDemand dataDependentCPUResourceDemand = extractDataDependentCPUResourceDemand(experiment, applicationID, stageAttempt);
			resourceProfile.getResourceDemands().add(dataDependentCPUResourceDemand);
		}
		ResourceDemand staticCPUResourceDemand = extractStaticCPUResourceDemand(experiment, applicationID, stageAttempt);
		resourceProfile.getResourceDemands().add(staticCPUResourceDemand);
		
		ResourceDemand overheadResourceDemand = extractExecutorOverhead(); // must be called last
		resourceProfile.getResourceDemands().add(overheadResourceDemand);
		
		ResourceDemand delayResourceDemand = extractDelayResourceDemand(applicationID, stageAttempt); // must be called last
		resourceProfile.getResourceDemands().add(delayResourceDemand);
		
		log(taskSummary, resourceProfile);
		return resourceProfile;
	}


	private void log(TaskSummaryDTO taskSummary, ResourceProfile resourceProfile) {
		log.info("Tasks Time" + Arrays.toString(taskSummary.getExecutorRunTime()) + "; Deserialization Time: " + Arrays.toString(taskSummary.getExecutorDeserializeTime()));
		log.info("Extracted resource demands are: ");
		for (ResourceDemand rd : resourceProfile.getResourceDemands()) {
			log.info("\t" + rd.getProcessingType() + ": " + rd.getRandomVariable());
		}
	}

	private ResourceDemand extractDataDependentCPUResourceDemand(String experiment, String applicationID, StageAttemptDTO stageAttempt) {
		ResourceDemand rd = factory.createResourceDemand();
		rd.setProcessingType(ProcessingType.CPU.getName());
		long aggregatedCPUTimeForProcessingData = agentClient.getCPUTime(
				experiment,
				"task", 
				stageAttempt.getFirstTaskLaunchedTime().toDate(), 
				stageAttempt.getCompletionTime().toDate());
		int tasks = stageAttempt.getNumCompleteTasks() + stageAttempt.getNumFailedTasks();
		long cpuTimePerMegaByte = 0;
		try {
			long meanInputMegaBytes = ((stageAttempt.getInputBytes()+stageAttempt.getShuffleReadBytes()) / bytes_to_megabytes);
			long cpuTimePerTask = aggregatedCPUTimeForProcessingData / tasks;
			double meanTaskTime = Arrays.stream(taskSummary.getExecutorRunTime()).sum()/taskSummary.getExecutorRunTime().length;
			if (cpuTimePerTask > meanTaskTime) {
				log.debug("Apparently, the measured CPU time per task (from the agent) exceeds the mean task response time (from Spark UI). "
						+ "Therefore, we overrule the CPU time and use the task response time as CPU resource demand.");
				log.debug(cpuTimePerTask + " " + meanInputMegaBytes);
				cpuTimePerTask = (long) meanTaskTime;
				aggregatedCPUTimeForProcessingData = cpuTimePerTask * tasks;
			}
			this.cpuTimePerTask += cpuTimePerTask;
			log.debug("CPU time per task = " + cpuTimePerTask);
			cpuTimePerMegaByte = (int) Math.ceil((double) aggregatedCPUTimeForProcessingData / meanInputMegaBytes);
		} catch (ArithmeticException e) {
		}
		log.debug("CPU Demand (data dependent): " + cpuTimePerMegaByte + " - stage " + stageAttempt.getStageId());
		rd.setRandomVariable(parametricDataVariableName + " * " + cpuTimePerMegaByte);
		rd.setParametricVariableName(parametricDataVariableName);
		return rd;
	}
	
	private ResourceDemand extractStaticCPUResourceDemand(String experiment, String applicationID, StageAttemptDTO stageAttempt) {
		ResourceDemand rd = factory.createResourceDemand();
		rd.setProcessingType(ProcessingType.CPU.getName());
		long aggregatedCPUTimeForRunningInfrastructure = agentClient.getCPUTime(
				experiment,
				"nio-worker", 
				stageAttempt.getFirstTaskLaunchedTime().toDate(), 
				stageAttempt.getCompletionTime().toDate());
		int tasks = stageAttempt.getNumCompleteTasks() + stageAttempt.getNumFailedTasks();
		long cpuTimePerTaskIndependentOfMegaBytes = 0;
		try {
			cpuTimePerTaskIndependentOfMegaBytes = (int) Math.ceil((double) aggregatedCPUTimeForRunningInfrastructure / tasks);			
			this.cpuTimePerTask += cpuTimePerTaskIndependentOfMegaBytes;
			log.debug("cpuTimePerTaskIndependentOfMegaBytes: " + cpuTimePerTaskIndependentOfMegaBytes);
		} catch (ArithmeticException e) {
		}
		log.debug("CPU Demand: " + cpuTimePerTaskIndependentOfMegaBytes + " - stage " + stageAttempt.getStageId());
		rd.setRandomVariable(String.valueOf(cpuTimePerTaskIndependentOfMegaBytes));
		return rd;
	}
	
	private ResourceDemand extractDriveReadResourceDemand(StageAttemptDTO stageAttempt) {
		ResourceDemand rd = factory.createResourceDemand();
		rd.setProcessingType(ProcessingType.DRIVE_READ.getName());
		int tasks = stageAttempt.getNumCompleteTasks() + stageAttempt.getNumFailedTasks();
		long readMegabytes = (stageAttempt.getInputBytes() + stageAttempt.getShuffleReadBytes()) / bytes_to_megabytes;
		return rd;
	}
	
	private ResourceDemand extractDriveWriteResourceDemand() {
		ResourceDemand rd = factory.createResourceDemand();
		rd.setProcessingType(ProcessingType.DRIVE_WRITE.getName());
		return rd;
	}
	
	private ResourceDemand extractMemoryResourceDemand() {
		ResourceDemand rd = factory.createResourceDemand();
		rd.setProcessingType(ProcessingType.MEMORY_ALLOCATED.getName());
		return rd;
	}
	
	/*
	 *  We calculate executor overhead similar to Apache Sparks implementations.
	 *  The only difference is that the totalExecutionTime is not available through the Spark API. 
	 *  (see my comment at https://stackoverflow.com/a/39081759/4073654)
	 */
	private ResourceDemand extractExecutorOverhead() {
		double[] executorOverhead = new double[taskSummary.getQuantiles().length];
		for (int i = 0; i < taskSummary.getQuantiles().length; i++) {
			executorOverhead[i] = taskSummary.getExecutorDeserializeTime()[i] + taskSummary.getResultSerializationTime()[i];
		}
		String executorOverheadDistribution = buildPdfWithUniqueValues(executorOverhead);
		
		ResourceDemand rd = factory.createResourceDemand();
		rd.setProcessingType(ProcessingType.CPU.getName());
		rd.setParametricVariableName(parametricExecutorVariableName);
		rd.setRandomVariable(executorOverheadDistribution);
		return rd;
	}


	/*
	 * This method transforms percentiles into a probability density function (pdf)
	 * in order to ensure unique, non-zero values during the simulation
	 * 
	 * It assumes an array of size 5, each element i describing the value for its
	 * percentile {0.05,0.25,0.5,0.75,0.95}
	 * 
	 * Examples:
	 * Input {0,5,10,15,20} becomes
	 * DoublePDF[(1;0.05)(5;0.25)(10;0.25)(15;0.25)(20;0.2)] * executors.BYTESIZE
	 * 
	 * Input {5,5,10,15,20} becomes
	 * DoublePDF[(5;0.3)(10;0.25)(15;0.25)(20;0.2)] * executors.BYTESIZE
	 * 
	 * Input {5,5,5,5,5} becomes
	 * 5 * executors.BYTESIZE
	 */
	private String buildPdfWithUniqueValues(double[] percentiles) {
		int executors = 4; // is the dependent variable
		double[] probabilites = {0.05,0.25,0.25,0.25,0.2};		
		SortedMap<Integer,Double> pdfMap = new TreeMap<Integer, Double>();
		for (int i = 0; i < percentiles.length; i++) {
			int dependentValue = (int) Math.ceil(percentiles[i] / executors);
			dependentValue = dependentValue == 0 ? 1 : dependentValue;
			if (pdfMap.containsKey(dependentValue)) {
				double aggregatedProbability = pdfMap.get(dependentValue) + probabilites[i];
				pdfMap.put(dependentValue, aggregatedProbability);
			} else {
				pdfMap.put(dependentValue, probabilites[i]);
			}
		}
		
		String randomVariable = "";
		if (pdfMap.size()==1) {
			randomVariable = String.valueOf(pdfMap.firstKey());
		} else {
			randomVariable = "DoublePDF[";
			for (Entry<Integer, Double> entry : pdfMap.entrySet()) {
				randomVariable += "("+ entry.getKey() + ";" + entry.getValue() + ")";
			}
			randomVariable += "]";
		}
		randomVariable += " * " + parametricExecutorVariableName;
		return randomVariable;
	}
	
	private ResourceDemand extractDelayResourceDemand(String applicationID, StageAttemptDTO stageAttempt) {	
		// since only the start time but not the end time of each task is available, we cannot calculate the schedulerDelay
		int schedulerDelay = 15; 
		int cpuDiff = calculateCPUDiff();	
		String delay = String.valueOf(cpuDiff + schedulerDelay);
		
		ResourceDemand rd = factory.createResourceDemand();
		rd.setProcessingType(ProcessingType.DELAY.getName());
		rd.setParametricVariableName(parametricExecutorVariableName);
		rd.setRandomVariable(delay);
		return rd;
	}
	
	private int calculateCPUDiff() {
		double meanExecutionTime = Arrays.stream(taskSummary.getExecutorRunTime()).sum()/taskSummary.getExecutorRunTime().length;
		int cpuDiff = (int) Math.ceil(Math.max(0, meanExecutionTime - cpuTimePerTask));	
		return cpuDiff;
	}

}
