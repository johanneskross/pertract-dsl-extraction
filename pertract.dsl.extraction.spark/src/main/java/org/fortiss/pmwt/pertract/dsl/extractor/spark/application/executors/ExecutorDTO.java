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
package org.fortiss.pmwt.pertract.dsl.extractor.spark.application.executors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value={"isActive", "totalCores", "maxTasks", "totalGCTime", "isBlacklisted", "memoryMetrics"})
public class ExecutorDTO {
	
	private String id;
	private String hostPort;
	private int rddBlocks;
	private int memoryUsed;
	private int diskUsed;
	private int activeTasks;
	private int failedTasks;
	private int completedTasks;
	private int totalTasks;
	private int totalDuration;
	private long totalInputBytes;
	private long totalShuffleRead;
	private long totalShuffleWrite;
	private long maxMemory;
	private ExecutorLogsDTO executorLogs;
	
	public ExecutorDTO(){
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHostPort() {
		return hostPort;
	}

	public void setHostPort(String hostPort) {
		this.hostPort = hostPort;
	}

	public int getRddBlocks() {
		return rddBlocks;
	}

	public void setRddBlocks(int rddBlocks) {
		this.rddBlocks = rddBlocks;
	}

	public int getMemoryUsed() {
		return memoryUsed;
	}

	public void setMemoryUsed(int memoryUsed) {
		this.memoryUsed = memoryUsed;
	}

	public int getDiskUsed() {
		return diskUsed;
	}

	public void setDiskUsed(int diskUsed) {
		this.diskUsed = diskUsed;
	}

	public int getActiveTasks() {
		return activeTasks;
	}

	public void setActiveTasks(int activeTasks) {
		this.activeTasks = activeTasks;
	}

	public int getFailedTasks() {
		return failedTasks;
	}

	public void setFailedTasks(int failedTasks) {
		this.failedTasks = failedTasks;
	}

	public int getCompletedTasks() {
		return completedTasks;
	}

	public void setCompletedTasks(int completedTasks) {
		this.completedTasks = completedTasks;
	}

	public int getTotalTasks() {
		return totalTasks;
	}

	public void setTotalTasks(int totalTasks) {
		this.totalTasks = totalTasks;
	}

	public int getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(int totalDuration) {
		this.totalDuration = totalDuration;
	}

	public long getTotalInputBytes() {
		return totalInputBytes;
	}

	public void setTotalInputBytes(long totalInputBytes) {
		this.totalInputBytes = totalInputBytes;
	}

	public long getTotalShuffleRead() {
		return totalShuffleRead;
	}

	public void setTotalShuffleRead(long totalShuffleRead) {
		this.totalShuffleRead = totalShuffleRead;
	}

	public long getTotalShuffleWrite() {
		return totalShuffleWrite;
	}

	public void setTotalShuffleWrite(long totalShuffleWrite) {
		this.totalShuffleWrite = totalShuffleWrite;
	}

	public long getMaxMemory() {
		return maxMemory;
	}

	public void setMaxMemory(long maxMemory) {
		this.maxMemory = maxMemory;
	}

	public ExecutorLogsDTO getExecutorLogs() {
		return executorLogs;
	}

	public void setExecutorLogs(ExecutorLogsDTO executorLogs) {
		this.executorLogs = executorLogs;
	}
}
