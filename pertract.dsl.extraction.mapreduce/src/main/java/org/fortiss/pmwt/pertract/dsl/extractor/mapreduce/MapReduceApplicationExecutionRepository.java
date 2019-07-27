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
package org.fortiss.pmwt.pertract.dsl.extractor.mapreduce;

import java.util.ArrayList;
import java.util.List;

import org.fortiss.pmwt.pertract.dsl.ApplicationExecutionArchitectureRepository;
import org.fortiss.pmwt.pertract.dsl.extractor.mapreduce.job.JobDTO;
import org.fortiss.pmwt.pertract.dsl.model.application.ApplicationDSLFactory;
import org.fortiss.pmwt.pertract.dsl.model.application.ApplicationExecutionArchitecture;

public class MapReduceApplicationExecutionRepository implements ApplicationExecutionArchitectureRepository {

	public static final String CPU_MILLISECONDS = "CPU_MILLISECONDS";
	public static final String TASK_COUNTER = "org.apache.hadoop.mapreduce.TaskCounter";
	public static final String FILE_SYSTEM_COUNTER = "org.apache.hadoop.mapreduce.FileSystemCounter";	
	
	private MapReduceDAO mapreduceClient;
	private ApplicationDSLFactory factory;
	
	public MapReduceApplicationExecutionRepository(String address) {
		this.mapreduceClient = new MapReduceDAO(address);
		this.factory = ApplicationDSLFactory.eINSTANCE;
	}
	
	public List<ApplicationExecutionArchitecture> findAll() {
		// For MapReduce, applications equal jobs --> we wrap each job with an application object
		List<ApplicationExecutionArchitecture> applications = new ArrayList<ApplicationExecutionArchitecture>();
		for (JobDTO jobDTO : mapreduceClient.getJobs()) {
			applications.add(this.find(jobDTO.getId()));
		}
		return applications;
	}
	
	public ApplicationExecutionArchitecture find(String applicationId) {
		// For MapReduce, an application equals a job --> we wrap a job with an application object
		ApplicationExecutionArchitecture application = factory.createApplicationExecutionArchitecture();
		application.setName(applicationId);
		application.setFramework("MAPREDUCE");
//		application.addComposite(this.createComposite(applicationId));
		return application;
	}
	
//	private Composite createComposite(String jobId) {		
//		JobDTO jobDTO = mapreduceClient.getJob(jobId);
//		Composite composite = new Composite();
//		composite.setName(jobDTO.getName());
//		composite.setComponents(this.createComponents(jobId));
//		return composite;
//	}
//	
//	private List<Component> createComponents(String jobId) {
//		List<Component> components = new ArrayList<Component>();
//		components.add(this.createMapComponent(jobId));
//		components.add(this.createReduceComponent(jobId));
//		return components;
//	}
//	
//	private Component createMapComponent(String jobId) {
//		Component component = new Component();
//		component.setName("Map");
//		component.setResourceDemands(this.createMapResourceDemands(jobId));
//		return component;
//	}
//	
//	private List<ResourceDemand> createMapResourceDemands(String jobId){
//		List<ResourceDemand> resourceDemands = new ArrayList<ResourceDemand>();
//		for (TaskDTO task : mapreduceClient.getMapTasks(jobId)) {
//			for (TaskAttemptDTO taskAttempt : mapreduceClient.getTaskAttempts(jobId, task.getId())) {
//				resourceDemands = this.fetchResourceDemandMeasurements(jobId, task.getId(), taskAttempt.getId());
//			}
//		}
//		return resourceDemands;
//	}
//	
//	private Component createReduceComponent(String jobId) {
//		Component component = new Component();
//		component.setName("Reduce");
//		component.setResourceDemands(this.createReduceResourceDemands(jobId));
//		return component;
//	}
//	
//	private List<ResourceDemand> createReduceResourceDemands(String jobId){
//		List<ResourceDemand> resourceDemands = new ArrayList<ResourceDemand>();
//		for (TaskDTO task : mapreduceClient.getReduceTasks(jobId)) {
//			for (TaskAttemptDTO taskAttempt : mapreduceClient.getTaskAttempts(jobId, task.getId())) {
//				resourceDemands = this.fetchResourceDemandMeasurements(jobId, task.getId(), taskAttempt.getId());
//			}
//		}
//		return resourceDemands;
//	}
//	
//	private List<ResourceDemand> fetchResourceDemandMeasurements(String jobId, String taskId, String taskAttemptId) {
//		List<ResourceDemand> resourceDemands = new ArrayList<ResourceDemand>();
//		DescriptiveStatistics cpuMeasurements = new DescriptiveStatistics();
//		List<JobTaskAttemptCounterGroupDTO> counterGroups = mapreduceClient.getTaskAttemptCounters(jobId, taskId, taskAttemptId);
//		for (JobTaskAttemptCounterGroupDTO counterGroup : counterGroups) {
//			if (counterGroup.getCounterGroupName().equals(TASK_COUNTER)) {
//				for (JobTaskAttemptCounterDTO counter : counterGroup.getCounter()) {
//					switch (counter.getName()) {
//					case CPU_MILLISECONDS:
//						cpuMeasurements.addValue(counter.getValue());
//						break;
//					}
//				}
//			}
//		}
//		ResourceDemand cpuResourceDemand = new ResourceDemand();
//		cpuResourceDemand.setResourceType(ResourceType.CPU);
//		cpuResourceDemand.setValue(cpuMeasurements.getPercentile(50));
//		resourceDemands.add(cpuResourceDemand);
//		
//		return resourceDemands;
//	}

}
