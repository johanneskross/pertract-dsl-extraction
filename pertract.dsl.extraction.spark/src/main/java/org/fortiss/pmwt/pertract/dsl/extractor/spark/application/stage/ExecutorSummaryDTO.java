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
package org.fortiss.pmwt.pertract.dsl.extractor.spark.application.stage;

public class ExecutorSummaryDTO {

	private int taskTime;
	private int failedTasks;
	private int succeededTasks;
	private long inputBytes;
	private long outputBytes;
	private long shuffleRead;
	private long shuffleWrite;
	private long memoryBytesSpilled;
	private long diskBytesSpilled;
	
	public ExecutorSummaryDTO(){
	}

	public int getTaskTime() {
		return taskTime;
	}

	public void setTaskTime(int taskTime) {
		this.taskTime = taskTime;
	}

	public int getFailedTasks() {
		return failedTasks;
	}

	public void setFailedTasks(int failedTasks) {
		this.failedTasks = failedTasks;
	}

	public int getSucceededTasks() {
		return succeededTasks;
	}

	public void setSucceededTasks(int succeededTasks) {
		this.succeededTasks = succeededTasks;
	}

	public long getInputBytes() {
		return inputBytes;
	}

	public void setInputBytes(long inputBytes) {
		this.inputBytes = inputBytes;
	}

	public long getOutputBytes() {
		return outputBytes;
	}

	public void setOutputBytes(long outputBytes) {
		this.outputBytes = outputBytes;
	}

	public long getShuffleRead() {
		return shuffleRead;
	}

	public void setShuffleRead(long shuffleRead) {
		this.shuffleRead = shuffleRead;
	}

	public long getShuffleWrite() {
		return shuffleWrite;
	}

	public void setShuffleWrite(long shuffleWrite) {
		this.shuffleWrite = shuffleWrite;
	}

	public long getMemoryBytesSpilled() {
		return memoryBytesSpilled;
	}

	public void setMemoryBytesSpilled(long memoryBytesSpilled) {
		this.memoryBytesSpilled = memoryBytesSpilled;
	}

	public long getDiskBytesSpilled() {
		return diskBytesSpilled;
	}

	public void setDiskBytesSpilled(long diskBytesSpilled) {
		this.diskBytesSpilled = diskBytesSpilled;
	}
}
