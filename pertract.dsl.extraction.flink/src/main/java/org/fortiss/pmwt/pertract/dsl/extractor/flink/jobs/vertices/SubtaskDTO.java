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
package org.fortiss.pmwt.pertract.dsl.extractor.flink.jobs.vertices;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.fortiss.pmwt.pertract.dsl.extractor.flink.jobs.MetricsDTO;

public class SubtaskDTO {
	
	private int subtask;
	private String status;
	private int attempt;
	private String host;
	@JsonProperty("start-time") private long startTime;
	@JsonProperty("end-time") private long endTime;
	private long duration; 
	private MetricsDTO metrics;
	
	public SubtaskDTO() {
	}

	public int getSubtask() {
		return subtask;
	}

	public void setSubtask(int subtask) {
		this.subtask = subtask;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getAttempt() {
		return attempt;
	}

	public void setAttempt(int attempt) {
		this.attempt = attempt;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
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

	public MetricsDTO getMetrics() {
		return metrics;
	}

	public void setMetrics(MetricsDTO metrics) {
		this.metrics = metrics;
	}
}
