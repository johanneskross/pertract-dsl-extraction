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

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JobDTO {

	private String jid;
	private String name;
	private String state;
	@JsonProperty("isStoppable") private boolean isStoppable;
	@JsonProperty("start-time") private long startTime;
	@JsonProperty("end-time") private long endTime;
	private long duration;
	private long now;
	private TimestampsDTO timestamps;
	private List<CompactVertexDTO> vertices;
	@JsonProperty("status-counts") private StatusCountsDTO statusCounts;
	private JobPlanDTO plan;
	
	public JobDTO() {
	}

	public String getJid() {
		return jid;
	}

	public void setJid(String jid) {
		this.jid = jid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public long getNow() {
		return now;
	}

	public void setNow(long now) {
		this.now = now;
	}

	public boolean isStoppable() {
		return isStoppable;
	}

	public void setStoppable(boolean isStoppable) {
		this.isStoppable = isStoppable;
	}

	public TimestampsDTO getTimestamps() {
		return timestamps;
	}

	public void setTimestamps(TimestampsDTO timestamps) {
		this.timestamps = timestamps;
	}

	public List<CompactVertexDTO> getVertices() {
		return vertices;
	}

	public void setVertices(List<CompactVertexDTO> vertices) {
		this.vertices = vertices;
	}

	public StatusCountsDTO getStatusCounts() {
		return statusCounts;
	}

	public void setStatusCount(StatusCountsDTO statusCounts) {
		this.statusCounts = statusCounts;
	}

	public JobPlanDTO getPlan() {
		return plan;
	}

	public void setPlan(JobPlanDTO plan) {
		this.plan = plan;
	}
	
}
