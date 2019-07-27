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

public class BoltDTO {

	private String boltId;
	private String encodedBoltId;
	private String capacity;
	private String processLatency;
	private String executeLatency;
	private int executors;
	private int tasks;
	private long acked;
	private long failed;
	private long transferred;
	private long executed;
	private String lastError;
	private String errorHost;
	private String errorPort;
	private int errorLapsedSecs;
	private String errorWorkerLogLink;
	private long emitted;
	
	public BoltDTO() {
	}

	public String getBoltId() {
		return boltId;
	}

	public void setBoltId(String boltId) {
		this.boltId = boltId;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getProcessLatency() {
		return processLatency;
	}

	public void setProcessLatency(String processLatency) {
		this.processLatency = processLatency;
	}

	public String getExecuteLatency() {
		return executeLatency;
	}

	public void setExecuteLatency(String executeLatency) {
		this.executeLatency = executeLatency;
	}

	public int getExecutors() {
		return executors;
	}

	public void setExecutors(int executors) {
		this.executors = executors;
	}

	public int getTasks() {
		return tasks;
	}

	public void setTasks(int tasks) {
		this.tasks = tasks;
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

	public long getTransferred() {
		return transferred;
	}

	public void setTransferred(long transferred) {
		this.transferred = transferred;
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

	public long getEmitted() {
		return emitted;
	}

	public void setEmitted(long emitted) {
		this.emitted = emitted;
	}

	public String getEncodedBoltId() {
		return encodedBoltId;
	}

	public void setEncodedBoltId(String encodedBoltId) {
		this.encodedBoltId = encodedBoltId;
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

	public long getExecuted() {
		return executed;
	}

	public void setExecuted(long executed) {
		this.executed = executed;
	}
	
}
