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
package org.fortiss.pmwt.pertract.dsl.extractor.storm;

public class SpoutDTO {

	private String spoutId;
	private String encodedSpoutId;
	private int executors;
	private long emitted;
	private String completeLatency;
	private long transferred;
	private int tasks;
	private String lastError;
	private int errorLapsedSecs;
	private String errorWorkerLogLink;
	private String errorHost;
	private String errorPort;
	private long acked;
	private long failed;
	
	public SpoutDTO() {
	}

	public String getSpoutId() {
		return spoutId;
	}

	public void setSpoutId(String spoutId) {
		this.spoutId = spoutId;
	}

	public String getEncodedSpoutId() {
		return encodedSpoutId;
	}

	public void setEncodedSpoutId(String encodedSpoutId) {
		this.encodedSpoutId = encodedSpoutId;
	}

	public int getExecutors() {
		return executors;
	}

	public void setExecutors(int executors) {
		this.executors = executors;
	}

	public long getEmitted() {
		return emitted;
	}

	public void setEmitted(long emitted) {
		this.emitted = emitted;
	}

	public String getCompleteLatency() {
		return completeLatency;
	}

	public void setCompleteLatency(String completeLatency) {
		this.completeLatency = completeLatency;
	}

	public long getTransferred() {
		return transferred;
	}

	public void setTransferred(long transferred) {
		this.transferred = transferred;
	}

	public int getTasks() {
		return tasks;
	}

	public void setTasks(int tasks) {
		this.tasks = tasks;
	}

	public String getLastError() {
		return lastError;
	}

	public void setLastError(String lastError) {
		this.lastError = lastError;
	}

	public int getErrorLapsedSecs() {
		return errorLapsedSecs;
	}

	public void setErrorLapsedSecs(int errorLapsedSecs) {
		this.errorLapsedSecs = errorLapsedSecs;
	}

	public String getErrorWorkerLogLink() {
		return errorWorkerLogLink;
	}

	public void setErrorWorkerLogLink(String errorWorkerLogLink) {
		this.errorWorkerLogLink = errorWorkerLogLink;
	}

	public String getErrorHost() {
		return errorHost;
	}

	public void setErrorHost(String errorHost) {
		this.errorHost = errorHost;
	}

	public String getErrorPort() {
		return errorPort;
	}

	public void setErrorPort(String errorPort) {
		this.errorPort = errorPort;
	}

	public long getAcked() {
		return acked;
	}

	public void setAcked(long acked) {
		this.acked = acked;
	}

	public long getFailed() {
		return failed;
	}

	public void setFailed(long failed) {
		this.failed = failed;
	}
}
