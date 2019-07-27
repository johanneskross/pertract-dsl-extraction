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
package org.fortiss.pmwt.pertract.dsl.extractor.mapreduce.job.task.attempt;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class TaskAttemptDTO {

	private String id;
	private String rack;
	private String state;
	private String status;
	private String type;
	private String assignedContainerId;
	private String nodeHttpAddress;
	private String diagnostics;
	private float progress;
	private DateTime startTime;
	private DateTime finishTime;
	private long elapsedTime;
	private long shuffleFinishTime;
	private long mergeFinishTime;
	private long elapsedShuffleTime;
	private long elapsedMergeTime;
	private long elapsedReduceTime;
	
	public TaskAttemptDTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRack() {
		return rack;
	}

	public void setRack(String rack) {
		this.rack = rack;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAssignedContainerId() {
		return assignedContainerId;
	}

	public void setAssignedContainerId(String assignedContainerId) {
		this.assignedContainerId = assignedContainerId;
	}

	public String getNodeHttpAddress() {
		return nodeHttpAddress;
	}

	public void setNodeHttpAddress(String nodeHttpAddress) {
		this.nodeHttpAddress = nodeHttpAddress;
	}

	public String getDiagnostics() {
		return diagnostics;
	}

	public void setDiagnostics(String diagnostics) {
		this.diagnostics = diagnostics;
	}

	public float getProgress() {
		return progress;
	}

	public void setProgress(float progress) {
		this.progress = progress;
	}

	public DateTime getStartTime() {
		return startTime;
	}

	public DateTime getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(long finishTime) {
		this.finishTime = new DateTime(finishTime, DateTimeZone.UTC);
	}

	public long getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public long getShuffleFinishTime() {
		return shuffleFinishTime;
	}

	public void setShuffleFinishTime(long shuffleFinishTime) {
		this.shuffleFinishTime = shuffleFinishTime;
	}

	public long getMergeFinishTime() {
		return mergeFinishTime;
	}

	public void setMergeFinishTime(long mergeFinishTime) {
		this.mergeFinishTime = mergeFinishTime;
	}

	public long getElapsedShuffleTime() {
		return elapsedShuffleTime;
	}

	public void setElapsedShuffleTime(long elapsedShuffleTime) {
		this.elapsedShuffleTime = elapsedShuffleTime;
	}

	public long getElapsedMergeTime() {
		return elapsedMergeTime;
	}

	public void setElapsedMergeTime(long elapsedMergeTime) {
		this.elapsedMergeTime = elapsedMergeTime;
	}

	public long getElapsedReduceTime() {
		return elapsedReduceTime;
	}

	public void setElapsedReduceTime(long elapsedReduceTime) {
		this.elapsedReduceTime = elapsedReduceTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = new DateTime(startTime, DateTimeZone.UTC);
	}
	
}
