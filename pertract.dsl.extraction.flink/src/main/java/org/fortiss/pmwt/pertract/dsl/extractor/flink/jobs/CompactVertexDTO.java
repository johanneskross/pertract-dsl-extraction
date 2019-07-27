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
package org.fortiss.pmwt.pertract.dsl.extractor.flink.jobs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompactVertexDTO {

	private String id;
	private String name;
	private int parallelism;
	private String status;
	@JsonProperty("start-time") private long startTime;
	@JsonProperty("end-time") private long endTime;
	private long duration;
	private TasksDTO tasks;
	private MetricsDTO metrics;
	
	public CompactVertexDTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParallelism() {
		return parallelism;
	}

	public void setParallelism(int parallelism) {
		this.parallelism = parallelism;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public TasksDTO getTasks() {
		return tasks;
	}

	public void setTasks(TasksDTO tasks) {
		this.tasks = tasks;
	}

	public MetricsDTO getMetrics() {
		return metrics;
	}

	public void setMetrics(MetricsDTO metrics) {
		this.metrics = metrics;
	}
}
