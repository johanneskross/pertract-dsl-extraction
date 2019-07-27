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
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import org.fortiss.pmwt.pertract.dsl.extractor.mapreduce.job.JobDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.mapreduce.job.JobsDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.mapreduce.job.configuration.ConfDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.mapreduce.job.counter.JobCounterGroupDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.mapreduce.job.counter.JobCountersDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.mapreduce.job.task.TaskDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.mapreduce.job.task.TasksDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.mapreduce.job.task.attempt.TaskAttemptDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.mapreduce.job.task.attempt.TaskAttemptsDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.mapreduce.job.task.attempt.counter.JobTaskAttemptCounterGroupDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.mapreduce.job.task.attempt.counter.JobTaskAttemptCountersDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.mapreduce.job.task.counter.JobTaskCounterGroupDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.mapreduce.job.task.counter.JobTaskCountersDTO;
import org.glassfish.jersey.client.ClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapReduceDAO {

	private Client client;
	private WebTarget webtarget;
	private static final String API_PATH = "/ws/v1/history/mapreduce/jobs";
	private final Logger log = LoggerFactory.getLogger(MapReduceDAO.class);
	
	public MapReduceDAO(String serverUrl){
		createClient();
		createWebresource(serverUrl + API_PATH);
	}
	
	private void createClient(){
		ClientConfig clientConfig = new ClientConfig();
		client = ClientBuilder.newClient(clientConfig);
	}
	
	private void createWebresource(String serverUrl){
		this.webtarget = client.target(serverUrl);
	}
	
	public Boolean testConnection() {
		try {
			webtarget.request().get(new GenericType<Map<String,JobsDTO>>(){});
		} catch (Exception e) {
			log.info("Exception", e);
			return false;
		}
		return true;
	}
	
	public List<JobDTO> getJobs() {
		Map<String,JobsDTO> jobsMap = webtarget
				.request().get(new GenericType<Map<String,JobsDTO>>(){});
		
		for (JobsDTO jobs : jobsMap.values()){
			return jobs.getJob();
		}
		return new ArrayList<JobDTO>();
	}
	
	public JobDTO getJob(String jobId){
		Map<String,JobDTO> jobMap = webtarget
				.path(jobId)
				.request().get(new GenericType<Map<String,JobDTO>>(){});
		
		for (JobDTO job : jobMap.values()){
			return job;
		}
		return new JobDTO();
	}
	
	public List<JobCounterGroupDTO> getCounterGroups(String jobId){
		Map<String,JobCountersDTO> jobCountersMap = webtarget
				.path(jobId)
				.path("counters")
				.request().get(new GenericType<Map<String,JobCountersDTO>>(){});
		
		for (JobCountersDTO jobCountersDTO : jobCountersMap.values()){
			return jobCountersDTO.getCounterGroup();
		}
		return new ArrayList<JobCounterGroupDTO>();
	}
	
	public ConfDTO getJobConfiguration(String jobId){
		Map<String, ConfDTO> confMap = webtarget
				.path(jobId)
				.path("conf")
				.request().get(new GenericType<Map<String,ConfDTO>>(){});
		
		for (ConfDTO conf : confMap.values()){
			return conf;
		}
		return new ConfDTO();
	}
	
	public List<TaskDTO> getTasks(String jobId) {
		Map<String, TasksDTO> tasksMap = webtarget
				.path(jobId)
				.path("tasks")
				.request().get(new GenericType<Map<String,TasksDTO>>(){});
		
		for (TasksDTO tasks : tasksMap.values()) {
			return tasks.getTask();
		}
		return new ArrayList<TaskDTO>();
	}
	
	public List<TaskDTO> getMapTasks(String jobId) {
		Map<String, TasksDTO> tasksMap = webtarget
				.path(jobId)
				.path("tasks")
				.queryParam("type", "m")
				.request().get(new GenericType<Map<String,TasksDTO>>(){});
		
		for (TasksDTO tasks : tasksMap.values()) {
			return tasks.getTask();
		}
		return new ArrayList<TaskDTO>();
	}
	
	public List<TaskDTO> getReduceTasks(String jobId) {
		Map<String, TasksDTO> tasksMap = webtarget
				.path(jobId)
				.path("tasks")
				.queryParam("type", "r")
				.request().get(new GenericType<Map<String,TasksDTO>>(){});
		
		for (TasksDTO tasks : tasksMap.values()) {
			return tasks.getTask();
		}
		return new ArrayList<TaskDTO>();
	}
	
	public List<JobTaskCounterGroupDTO> getJobTaskCounters(String jobId, String taskId){
		Map<String, JobTaskCountersDTO> jobTaskCountersMap = webtarget
				.path(jobId)
				.path("tasks")
				.path(taskId)
				.path("counters")
				.request().get(new GenericType<Map<String,JobTaskCountersDTO>>(){});
		
		for (JobTaskCountersDTO jobTaskCounters : jobTaskCountersMap.values()){
			return jobTaskCounters.getTaskCounterGroup();
		}
		return new ArrayList<JobTaskCounterGroupDTO>();
	}
	
	public List<TaskAttemptDTO> getTaskAttempts(String jobId, String taskId) {
		Map<String,TaskAttemptsDTO> taskAttemptsMap = webtarget
				.path(jobId)
				.path("tasks")
				.path(taskId)
				.path("attempts")
				.request().get(new GenericType<Map<String,TaskAttemptsDTO>>(){});
		
		for (TaskAttemptsDTO taskAttempts : taskAttemptsMap.values()){
			return taskAttempts.getTaskAttempt();
		}
		return new ArrayList<TaskAttemptDTO>();
	}
	
	public List<JobTaskAttemptCounterGroupDTO> getTaskAttemptCounters(String jobId, String taskId, String attemptId) {
		Map<String, JobTaskAttemptCountersDTO> jobTaskAttemptCountersMap = webtarget
				.path(jobId)
				.path("tasks")
				.path(taskId)
				.path("attempts")
				.path(attemptId)
				.path("counters")
				.request().get(new GenericType<Map<String,JobTaskAttemptCountersDTO>>(){});
		
		for (JobTaskAttemptCountersDTO jobTaskAttemptCounters : jobTaskAttemptCountersMap.values()){
			return jobTaskAttemptCounters.getTaskAttemptCounterGroup();
		}
		return new ArrayList<JobTaskAttemptCounterGroupDTO>();
	}
	
}
