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
package org.fortiss.pmwt.pertract.dsl.extractor.spark.application.stage.task;

import java.util.List;

import org.fortiss.pmwt.pertract.dsl.extractor.spark.application.SparkDAO;
import org.fortiss.pmwt.pertract.dsl.extractor.spark.application.stage.AccumulatorUpdateDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.spark.application.stage.task.metrics.TaskMetricsDTO;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class TaskDTO {

	private int taskId;
	private int index;
	private int attempt;
	private DateTime launchTime;
	private String executorId;
	private String host;
	private String taskLocality;
	private long duration;
	private boolean speculative;
	private List<AccumulatorUpdateDTO> accumulatorUpdates;
	private TaskMetricsDTO taskMetrics;
	private String status;
	
	public TaskDTO(){
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getAttempt() {
		return attempt;
	}

	public void setAttempt(int attempt) {
		this.attempt = attempt;
	}

	public DateTime getLaunchTime() {
		return launchTime;
	}

	public void setLaunchTime(String launchTime) {
		DateTimeFormatter parser = DateTimeFormat.forPattern(SparkDAO.DATE_TIME_FORMAT);
		this.launchTime = parser.parseDateTime(launchTime);
	}

	public String getExecutorId() {
		return executorId;
	}

	public void setExecutorId(String executorId) {
		this.executorId = executorId;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getTaskLocality() {
		return taskLocality;
	}

	public void setTaskLocality(String taskLocality) {
		this.taskLocality = taskLocality;
	}

	public boolean isSpeculative() {
		return speculative;
	}

	public void setSpeculative(boolean speculative) {
		this.speculative = speculative;
	}

	public List<AccumulatorUpdateDTO> getAccumulatorUpdates() {
		return accumulatorUpdates;
	}

	public void setAccumulatorUpdates(List<AccumulatorUpdateDTO> accumulatorUpdates) {
		this.accumulatorUpdates = accumulatorUpdates;
	}

	public TaskMetricsDTO getTaskMetrics() {
		return taskMetrics;
	}

	public void setTaskMetrics(TaskMetricsDTO taskMetrics) {
		this.taskMetrics = taskMetrics;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
