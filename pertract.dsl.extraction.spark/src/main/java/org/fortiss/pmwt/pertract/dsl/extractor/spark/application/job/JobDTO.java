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
package org.fortiss.pmwt.pertract.dsl.extractor.spark.application.job;

import java.util.Arrays;

import org.fortiss.pmwt.pertract.dsl.extractor.spark.application.SparkDAO;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class JobDTO{
	
	private int jobId;
	private String description;
	private String name;
	private DateTime submissionTime;
	private DateTime completionTime;
	private int[] stageIds;
	private String status;
	private int numTasks;
	private int numActiveTasks;
	private int numCompletedTasks;
	private int numSkippedTasks;
	private int numFailedTasks;
	private int numActiveStages;
	private int numCompletedStages;
	private int numSkippedStages;
	private int numFailedStages;
	
	public JobDTO(){
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DateTime getSubmissionTime() {
		return submissionTime;
	}

	public void setSubmissionTime(String submissionTime) {
		DateTimeFormatter parser = DateTimeFormat.forPattern(SparkDAO.DATE_TIME_FORMAT).withZoneUTC();
		this.submissionTime = parser.parseDateTime(submissionTime);
	}

	public DateTime getCompletionTime() {
		return completionTime;
	}

	public void setCompletionTime(String completionTime) {
		DateTimeFormatter parser = DateTimeFormat.forPattern(SparkDAO.DATE_TIME_FORMAT).withZoneUTC();
		this.completionTime = parser.parseDateTime(completionTime);
	}

	public int[] getStageIds() {
		return stageIds;
	}

	public void setStageIds(int[] stageIds) {
		this.stageIds = stageIds;
		Arrays.sort(stageIds);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getNumTasks() {
		return numTasks;
	}

	public void setNumTasks(int numTasks) {
		this.numTasks = numTasks;
	}

	public int getNumActiveTasks() {
		return numActiveTasks;
	}

	public void setNumActiveTasks(int numActiveTasks) {
		this.numActiveTasks = numActiveTasks;
	}

	public int getNumCompletedTasks() {
		return numCompletedTasks;
	}

	public void setNumCompletedTasks(int numCompletedTasks) {
		this.numCompletedTasks = numCompletedTasks;
	}

	public int getNumSkippedTasks() {
		return numSkippedTasks;
	}

	public void setNumSkippedTasks(int numSkippedTasks) {
		this.numSkippedTasks = numSkippedTasks;
	}

	public int getNumFailedTasks() {
		return numFailedTasks;
	}

	public void setNumFailedTasks(int numFailedTasks) {
		this.numFailedTasks = numFailedTasks;
	}

	public int getNumActiveStages() {
		return numActiveStages;
	}

	public void setNumActiveStages(int numActiveStages) {
		this.numActiveStages = numActiveStages;
	}

	public int getNumCompletedStages() {
		return numCompletedStages;
	}

	public void setNumCompletedStages(int numCompletedStages) {
		this.numCompletedStages = numCompletedStages;
	}

	public int getNumSkippedStages() {
		return numSkippedStages;
	}

	public void setNumSkippedStages(int numSkippedStages) {
		this.numSkippedStages = numSkippedStages;
	}

	public int getNumFailedStages() {
		return numFailedStages;
	}

	public void setNumFailedStages(int numFailedStages) {
		this.numFailedStages = numFailedStages;
	}
}
