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

import org.fortiss.pmwt.pertract.dsl.extractor.flink.jobs.MetricsDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.flink.jobs.StatusCountsDTO;

public class TaskManagerDTO {

	private String host;
	private String status;
	private long startTime;
	private long endTime;
	private long duration;
	private MetricsDTO metrics;
	private StatusCountsDTO statusCounts;
	
	public TaskManagerDTO() {
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
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

	public MetricsDTO getMetrics() {
		return metrics;
	}

	public void setMetrics(MetricsDTO metrics) {
		this.metrics = metrics;
	}

	public StatusCountsDTO getStatusCounts() {
		return statusCounts;
	}

	public void setStatusCounts(StatusCountsDTO statusCounts) {
		this.statusCounts = statusCounts;
	}
	
}
