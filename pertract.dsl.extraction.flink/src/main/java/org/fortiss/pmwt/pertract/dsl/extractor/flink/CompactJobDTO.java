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
package org.fortiss.pmwt.pertract.dsl.extractor.flink;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompactJobDTO {

	private String jid;
	private String name;
	private String state;
	@JsonProperty("start-time") private long startTime;
	@JsonProperty("end-time") private long endTime;
	private long duration;
	@JsonProperty("last-modification") private long lastModification;
	private TasksDTO tasks;
	
	public CompactJobDTO() {
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

	public long getLastModification() {
		return lastModification;
	}

	public void setLastModification(long lastModification) {
		this.lastModification = lastModification;
	}

	public TasksDTO getTasks() {
		return tasks;
	}

	public void setTasks(TasksDTO tasks) {
		this.tasks = tasks;
	}
	
}
