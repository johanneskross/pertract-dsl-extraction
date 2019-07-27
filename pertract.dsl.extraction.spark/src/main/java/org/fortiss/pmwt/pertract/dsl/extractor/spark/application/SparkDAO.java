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
package org.fortiss.pmwt.pertract.dsl.extractor.spark.application;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import org.fortiss.pmwt.pertract.dsl.extractor.spark.application.executors.ExecutorDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.spark.application.job.JobDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.spark.application.stage.StageAttemptDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.spark.application.stage.StageDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.spark.application.stage.task.TaskDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.spark.application.stage.task.TaskSummaryDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.spark.environment.EnvironmentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SparkDAO {
	
	private Client client;
	private WebTarget webtarget;
	private static final String API_PATH = "/api/v1/applications";
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'GMT'";
	private final Logger log = LoggerFactory.getLogger(SparkDAO.class);
	
	public SparkDAO(String serverUrl){
		createClient();
		createWebtarget(serverUrl + API_PATH);
	}
	
	private void createClient(){
		client = ClientBuilder.newClient();
	}
	
	private void createWebtarget(String serverUrl){
		this.webtarget = client.target(serverUrl);
	}
	
	public Boolean testConnection() {
		try {
			webtarget.request().get(new GenericType<List<ApplicationDTO>>(){});
		} catch (Exception e) {
			log.info("Exception", e);
			return false;
		}
		return true;
	}
	
	public List<ApplicationDTO> getApplications(){
		List<ApplicationDTO> applications = webtarget
		.request().get(new GenericType<List<ApplicationDTO>>(){});
		return applications;
	}
	
	public ApplicationDTO getApplication(String applicationId){
		ApplicationDTO application = webtarget
		.path(applicationId)
		.request().get(new GenericType<ApplicationDTO>(){});
		return application;
	}
	
	public String getApplicationType(String applicationId){
		try {
			StageAttemptDTO attempt = webtarget
					.path(applicationId)
					.path("stages")
					.path("0")
					.path("0")
					.request().get(new GenericType<StageAttemptDTO>(){});
			if (attempt.getDetails().contains("stream")) {
				return "MINI_BATCH";
			} else {
				return "BATCH";
			}
		} catch (Exception e) {
			log.info("Exception", e);
			return "BATCH";
		}
	}
	
	public ArrayList<JobDTO> getJobs(String applicationId){
		ArrayList<JobDTO> jobs = webtarget
		.path(applicationId)
		.path("jobs")
		.request().get(new GenericType<ArrayList<JobDTO>>(){});
		jobs.sort((JobDTO j1, JobDTO j2) -> j1.getJobId()-j2.getJobId());
		return jobs;
	}
	
	public JobDTO getJob(String applicationId, int jobId){
		JobDTO job = webtarget
		.path(applicationId)
		.path("jobs")
		.path(Integer.toString(jobId))
		.request().get(new GenericType<JobDTO>(){});
		return job;
	}
	
	public List<ExecutorDTO> getExecutors(String applicationId){
		List<ExecutorDTO> executors = webtarget
		.path(applicationId)
		.path("executors")
		.request().get(new GenericType<List<ExecutorDTO>>(){});
		return executors;
	}
	
	public List<StageDTO> getStages(String applicationId){
		List<StageDTO> stages = webtarget
				.path(applicationId)
				.path("stages")
				.request().get(new GenericType<List<StageDTO>>(){});
		stages.sort((StageDTO s1, StageDTO s2) -> s1.getStageId()-s2.getStageId());
		return stages;
	}
	
	public List<StageAttemptDTO> getStageAttempts(String applicationId, int stageId){
		List<StageAttemptDTO> stageAttempts = webtarget
				.path(applicationId)
				.path("stages")
				.path(Integer.toString(stageId))
				.request().get(new GenericType<List<StageAttemptDTO>>(){});
		stageAttempts.sort((StageAttemptDTO s1, StageAttemptDTO s2) -> s1.getAttemptId()-s2.getAttemptId());
		return stageAttempts;
	}
	
	public StageAttemptDTO getStageAttempt(String applicationId, int stageId, int attemptId){
		StageAttemptDTO stageAttempt = webtarget
				.path(applicationId)
				.path("stages")
				.path(Integer.toString(stageId))
				.path(Integer.toString(attemptId))
				.request().get(new GenericType<StageAttemptDTO>(){});
				return stageAttempt;
	}
	
	public List<TaskDTO> getTaskListOfStageAttempt(String applicationId, int stageId, int attemptId){
		List<TaskDTO> taskList = webtarget
				.path(applicationId)
				.path("stages")
				.path(Integer.toString(stageId))
				.path(Integer.toString(attemptId))
				.path("taskList")
				.request().get(new GenericType<List<TaskDTO>>(){});
				return taskList;
	}
	
	public TaskSummaryDTO getTaskSummaryOfStageAttempt(String applicationId, int stageId, int attemptId){
		TaskSummaryDTO taskList = webtarget
				.path(applicationId)
				.path("stages")
				.path(Integer.toString(stageId))
				.path(Integer.toString(attemptId))
				.path("taskSummary")
				.request().get(new GenericType<TaskSummaryDTO>(){});
				return taskList;
	}
	
	public EnvironmentDTO getEnvironment(String applicationId){
		EnvironmentDTO application = webtarget
		.path(applicationId)
		.path("environment")
		.request().get(new GenericType<EnvironmentDTO>(){});
		return application;
	}
	
	public static void main (String[] args) {
		SparkDAO spark = new SparkDAO("http://jkr-hadoop-master.plab.fortiss.org:18081");
		for (ApplicationDTO app : spark.getApplications()) {		
			String id = app.getId();
			String name = app.getName();
			
			long startTime = System.currentTimeMillis();
			long endTime = 0;
			for (JobDTO job : spark.getJobs(id)) {
				long tmpStartTime = job.getSubmissionTime().getMillis();
				long tmpEndTime = job.getCompletionTime().getMillis();
				startTime = tmpStartTime > startTime ? startTime : tmpStartTime;
				endTime = tmpEndTime > endTime ? tmpEndTime : endTime;
			}
			
			long time = (endTime - startTime) / 1000;
			System.out.println(id + ";" + name + ";" + time);
		}
	}

}
