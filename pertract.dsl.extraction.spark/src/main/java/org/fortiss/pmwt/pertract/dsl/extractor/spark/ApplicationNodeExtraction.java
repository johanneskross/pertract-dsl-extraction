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

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.common.util.EList;
import org.fortiss.pmwt.pertract.dsl.extractor.spark.application.ApplicationDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.spark.application.SparkDAO;
import org.fortiss.pmwt.pertract.dsl.extractor.spark.application.job.JobDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.spark.application.stage.StageAttemptDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.spark.application.stage.StageDTO;
import org.fortiss.pmwt.pertract.dsl.model.application.ApplicationConfiguration;
import org.fortiss.pmwt.pertract.dsl.model.application.ApplicationDSLFactory;
import org.fortiss.pmwt.pertract.dsl.model.application.DirectedEdge;
import org.fortiss.pmwt.pertract.dsl.model.application.ExecutionNode;
import org.fortiss.pmwt.pertract.dsl.model.application.Operation;
import org.fortiss.pmwt.pertract.dsl.model.application.ProcessingType;
import org.fortiss.pmwt.pertract.dsl.model.application.ResourceDemand;
import org.fortiss.pmwt.pertract.dsl.model.application.ResourceProfile;
import org.joda.time.DateTime;
import org.joda.time.base.BaseDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationNodeExtraction {

	private ApplicationDSLFactory factory;
	private SparkDAO sparkDAO;
	private ResourceProfileExtraction resourceProfileExtraction;
	private ArrayList<Long> dataInputOfFirstStagesOfJobs;
	final Logger log = LoggerFactory.getLogger(ApplicationNodeExtraction.class);
	
	public ApplicationNodeExtraction(SparkDAO sparkDAO) {
		this.factory = ApplicationDSLFactory.eINSTANCE;
		this.sparkDAO = sparkDAO;
		this.resourceProfileExtraction = new ResourceProfileExtraction(sparkDAO);
		this.dataInputOfFirstStagesOfJobs = new ArrayList<>();
	}
	
	public ExecutionNode extractNodeGraph(ApplicationDTO applicationDTO, ApplicationConfiguration config) {
		ExecutionNode topNode = factory.createExecutionNode();
		topNode.setStartingNode(true);
		topNode.setName("hiddenTopNode");
		return buildNodeGraphForJobs(topNode, applicationDTO, config);
	}
	
	private ExecutionNode buildNodeGraphForJobs(ExecutionNode topNode, ApplicationDTO applicationDTO, ApplicationConfiguration config) {
		List<Integer> completedStageIds = this.fetchCompletedStageIds(applicationDTO); // already fetched here to avoid many REST calls
		DateTime previousJobCompletionTime = new DateTime();
		ListIterator<JobDTO> jobs = sparkDAO.getJobs(applicationDTO.getId()).listIterator(0);
		while (jobs.hasNext()) {
			JobDTO jobDTO = jobs.next();
			if (jobDTO.getStatus().equals("SUCCEEDED")) {
				addDelayNodeBetweenJobs(topNode, jobDTO, previousJobCompletionTime, config);
				ExecutionNode jobNode = extractNode(applicationDTO, jobDTO);
				buildNodeGraphForStages(jobNode, applicationDTO, jobDTO, completedStageIds, config);
				topNode.getChildren().add(jobNode);
				if (jobs.previousIndex()==0) {
					jobNode.setStartingNode(true);
				}else {
					log.debug("top node children size " + topNode.getChildren().size());
					double dataTransmissionFactor = extractTransmissionFactorBetweenJobs(topNode.getChildren(), topNode.getChildren().size()-1);
					DirectedEdge edge = buildDirectedEdge(topNode.getChildren().get(topNode.getChildren().size()-2), jobNode, dataTransmissionFactor);
					topNode.getEdgesOfChildren().add(edge);
				}
			}
			previousJobCompletionTime = jobDTO.getCompletionTime();
 		}
		return topNode;
	}
	
	private void addDelayNodeBetweenJobs(ExecutionNode topNode, JobDTO jobDTO, BaseDateTime previousJobCompletionTime, ApplicationConfiguration config) {
		long timeBetweenJobs = jobDTO.getSubmissionTime().getMillis() - previousJobCompletionTime.getMillis();
		if (previousJobCompletionTime.isBefore(jobDTO.getCompletionTime()) && timeBetweenJobs >= 100) {
			ExecutionNode node = factory.createExecutionNode();
			node.setName("jobDelay"+jobDTO.getJobId());
			node.setParent(topNode);
			ResourceProfile resourceProfile = factory.createResourceProfile();
			ResourceDemand rdDelay = factory.createResourceDemand();
			rdDelay.setProcessingType(ProcessingType.DELAY.getName());
			if (dataInputOfFirstStagesOfJobs.size() > 0) {
				long lastDataInputInMB = dataInputOfFirstStagesOfJobs.get(dataInputOfFirstStagesOfJobs.size()-1) / 1024 / 1024;
				double randomVariable = (double) timeBetweenJobs / (double) lastDataInputInMB;
				rdDelay.setRandomVariable("dataSize.VALUE * " + randomVariable);
				rdDelay.setParametricVariableName("dataSize.VALUE");
			} else {
				rdDelay.setRandomVariable("" + timeBetweenJobs);
			}
			resourceProfile.getResourceDemands().add(rdDelay);
			ResourceDemand rdCPU = factory.createResourceDemand();
			rdCPU.setProcessingType(ProcessingType.CPU.getName());
			rdCPU.setRandomVariable("0");
			resourceProfile.getResourceDemands().add(rdCPU);
			node.setResourceProfile(resourceProfile);
			DirectedEdge edge = buildDirectedEdge(topNode.getChildren().get(topNode.getChildren().size()-2), node, 1.0);
			topNode.getEdgesOfChildren().add(edge);
			topNode.getChildren().add(node);
			log.info("Added delay before job with id " + jobDTO.getJobId());
		}
	}
	
	private void addDelayNodeBetweenStages(ExecutionNode topNode, ApplicationDTO applicationDTO, JobDTO jobDTO, StageAttemptDTO stageAttemptDTO) {
		long delay = 0;
		long dataInputOfPreviousStage = 0;
		boolean isFirstStageOfJob;
		if (stageAttemptDTO.getStageId() == jobDTO.getStageIds()[0]) {
			delay = stageAttemptDTO.getSubmissionTime().getMillis() - jobDTO.getSubmissionTime().getMillis();
			dataInputOfPreviousStage = stageAttemptDTO.getInputBytes() + stageAttemptDTO.getShuffleReadBytes();
			isFirstStageOfJob = true;
		} else {
			StageAttemptDTO previousStageAttemptDTO = findPreviousStageAttemptDTO(applicationDTO, jobDTO, stageAttemptDTO);	
			delay = stageAttemptDTO.getSubmissionTime().getMillis() - previousStageAttemptDTO.getCompletionTime().getMillis();
			dataInputOfPreviousStage = previousStageAttemptDTO.getInputBytes() + previousStageAttemptDTO.getShuffleReadBytes();
			isFirstStageOfJob = false;
		}
		if (delay > 500) {
			ExecutionNode node = factory.createExecutionNode();
			node.setName("stageDelay" + stageAttemptDTO.getStageId());
			node.setParent(topNode);
			ResourceProfile resourceProfile = factory.createResourceProfile();
			ResourceDemand rdDelay = factory.createResourceDemand();
			rdDelay.setProcessingType(ProcessingType.DELAY.getName());
			long dataInputInMB = (stageAttemptDTO.getInputBytes() + stageAttemptDTO.getShuffleReadBytes()) / 1024 / 1024;
			double randomVariable = (double) delay / (double) dataInputInMB;
			rdDelay.setRandomVariable("dataSize.VALUE * " + randomVariable);
			rdDelay.setParametricVariableName("dataSize.VALUE");
			resourceProfile.getResourceDemands().add(rdDelay);
			ResourceDemand rdCPU = factory.createResourceDemand();
			rdCPU.setProcessingType(ProcessingType.CPU.getName());
			rdCPU.setRandomVariable("0");
			resourceProfile.getResourceDemands().add(rdCPU);
			node.setResourceProfile(resourceProfile);
			if (! isFirstStageOfJob) {
				double transmissionFactor = extractDataTransmissionFactor(stageAttemptDTO, dataInputOfPreviousStage);
				DirectedEdge edge = buildDirectedEdge(topNode.getChildren().get(topNode.getChildren().size()-1), node, transmissionFactor);
				topNode.getEdgesOfChildren().add(edge);
			}
			topNode.getChildren().add(node);
			log.info("Added delay before stage with id " + stageAttemptDTO.getStageId());
		}
	}
	
	private StageAttemptDTO findPreviousStageAttemptDTO (ApplicationDTO applicationDTO, JobDTO jobDTO, StageAttemptDTO stageAttemptDTO) {
		for (int previousStageId : jobDTO.getStageIds()) {
			if (previousStageId == (stageAttemptDTO.getStageId() - 1)) {
				for (StageAttemptDTO previousStageAttemptDTO : sparkDAO.getStageAttempts(applicationDTO.getId(), previousStageId)) {
					if (previousStageAttemptDTO.getStatus().equals("COMPLETE")) {	
						return previousStageAttemptDTO;
					}
				}
			}
		}
		return new StageAttemptDTO();
	}

	private ExecutionNode buildNodeGraphForStages(ExecutionNode parent, ApplicationDTO applicationDTO, JobDTO jobDTO, List<Integer> completedStageIds, ApplicationConfiguration config) {
		int index = 0;
		long dataInputOfPreviousNode = 0;
		for (int stageId : jobDTO.getStageIds()){
			if (!completedStageIds.contains(stageId)) continue;
			for (StageAttemptDTO stageAttemptDTO : sparkDAO.getStageAttempts(applicationDTO.getId(), stageId)){
				if (stageAttemptDTO.getStatus().equals("COMPLETE")) {	
					addDelayNodeBetweenStages(parent, applicationDTO, jobDTO, stageAttemptDTO);
					ExecutionNode node = extractNode(parent, applicationDTO, stageAttemptDTO, config);
					if(index == 0) {
						node.setStartingNode(true);
						long dataInput = stageAttemptDTO.getInputBytes() + stageAttemptDTO.getShuffleReadBytes();
						this.dataInputOfFirstStagesOfJobs.add(dataInput);
						dataInputOfPreviousNode = dataInput;
					}
					if (parent.getChildren().size() > 0) {
						if (parent.getEdgesOfChildren().size() > 0) {
							double previousDataTransmissionFactor = parent.getEdgesOfChildren().get(parent.getEdgesOfChildren().size()-1).getDataTransmissionFactor();
							dataInputOfPreviousNode = (long) (dataInputOfPreviousNode * previousDataTransmissionFactor);
						}
						double dataTransmissionFactor = extractDataTransmissionFactor(stageAttemptDTO, dataInputOfPreviousNode);
						DirectedEdge edge = buildDirectedEdge(parent.getChildren().get(parent.getChildren().size()-1), node, dataTransmissionFactor);
						parent.getEdgesOfChildren().add(edge);
					}
					parent.getChildren().add(node);
					index++;
				}
			}
		}
		return parent;
	}
	
	private List<Integer> fetchCompletedStageIds(ApplicationDTO applicationDTO) {
		List<Integer> completedStageIds = new ArrayList<Integer>();
		for (StageDTO stageDTO : sparkDAO.getStages(applicationDTO.getId())) {
			completedStageIds.add(stageDTO.getStageId());
		}
		return completedStageIds;
	}
	
	private DirectedEdge buildDirectedEdge(ExecutionNode tail, ExecutionNode head, double bytesRelativeToInput) {
		DirectedEdge directedEdge = factory.createDirectedEdge();
		directedEdge.setTail(tail);
		directedEdge.setHead(head);
		directedEdge.setDataTransmissionFactor(bytesRelativeToInput);
		return directedEdge;
	}
		
	private List<Operation> createOperations(String details) {
		List<Operation> operations = new ArrayList<>();
		for (String operation : details.split("\n")) {
			operations.add(createOperation(operation));
		}
		return operations;
	}
	
	private Operation createOperation(String name) {
		Operation operation = factory.createOperation();
		operation.setName(name);
		return operation;
	}
	
	private ExecutionNode extractNode(ApplicationDTO applicationDTO, JobDTO jobDTO) {
		ExecutionNode node = factory.createExecutionNode();
		node.setName("job" + jobDTO.getJobId());
		log.info("Extracted node for job " + jobDTO.getJobId());
		return node;
	}
	
	private ExecutionNode extractNode(ExecutionNode parent, ApplicationDTO applicationDTO, StageAttemptDTO stageAttemptDTO, ApplicationConfiguration config) {
		ExecutionNode node = factory.createExecutionNode();
		node.setParent(parent);
		node.setName("stage" + stageAttemptDTO.getStageId());
		node.getOperations().addAll(createOperations(stageAttemptDTO.getDetails()));
		ResourceProfile resourceProfile = resourceProfileExtraction.extractResourceProfile(applicationDTO.getId(), stageAttemptDTO);
		node.setResourceProfile(resourceProfile);
		if (stageAttemptDTO.getNumCompleteTasks()==config.getDefaultParallelism()) {
			node.setParallelism(config.getDefaultParallelism());
		} else if (stageAttemptDTO.getNumCompleteTasks()==1) {
			node.setParallelism(1);
		} else {
			node.setSpout(true);
		}
		log.info("Extracted node for stage " + stageAttemptDTO.getStageId() + "; node parallelism: " + node.getParallelism() + "; spout: " + node.isSpout());
		return node;
	}
	
	private double extractDataTransmissionFactor(StageAttemptDTO stageAttemptDTO, long dataInputOfPreviousStage) {
		return (double)(stageAttemptDTO.getInputBytes()+stageAttemptDTO.getShuffleReadBytes()) / (double) dataInputOfPreviousStage;
	}
	
	private double extractTransmissionFactorBetweenJobs(EList<ExecutionNode> nodes, int index) {
		ExecutionNode firstStageOfPreviousJob;
		if (index>=nodes.size()) {
			throw new IllegalArgumentException("Index out of bound - "
					+ "size of nodes list: " + nodes.size() + " - requested index : " + index);
		}
		
		if (nodes.get(index-1).getChildren().size()>0) {
			firstStageOfPreviousJob = nodes.get(index-1).getChildren().get(0);
		} else {
			firstStageOfPreviousJob = nodes.get(index-2).getChildren().get(0);
		}
		if (firstStageOfPreviousJob.isStartingNode() && !firstStageOfPreviousJob.isSpout()) {
			return 1.0;
		} else {
			int size = dataInputOfFirstStagesOfJobs.size();
			return (double) dataInputOfFirstStagesOfJobs.get(size-1) / (double) dataInputOfFirstStagesOfJobs.get(size-2);
		}
	}

	
}
