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

import java.util.List;
import java.util.Map;

import org.fortiss.pmwt.pertract.dsl.extractor.spark.application.stage.task.TaskDTO;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class StageAttemptDTO {
	
	private String status;
	private int stageId;
	private int attemptId;
	private int numActiveTasks;
	private int numCompleteTasks;
	private int numFailedTasks;
	private long executorRunTime;
	private long executorCpuTime;
	private DateTime submissionTime;
	private DateTime firstTaskLaunchedTime;
	private DateTime completionTime;
	private long inputBytes;
	private long inputRecords;
	private long outputBytes;
	private long outputRecords;
	private long shuffleReadBytes;
	private long shuffleReadRecords;
	private long shuffleWriteBytes;
	private long shuffleWriteRecords;
	private long memoryBytesSpilled;
	private long diskBytesSpilled;
	private String name;
	private String details;
	private String schedulingPool;
	private List<AccumulatorUpdateDTO> accumulatorUpdates;
	private Map<String,TaskDTO> tasks;
	private Map<String,ExecutorSummaryDTO> executorSummary;
	
	public StageAttemptDTO(){
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getStageId() {
		return stageId;
	}

	public void setStageId(int stageId) {
		this.stageId = stageId;
	}

	public int getAttemptId() {
		return attemptId;
	}

	public void setAttemptId(int attemptId) {
		this.attemptId = attemptId;
	}

	public int getNumActiveTasks() {
		return numActiveTasks;
	}

	public void setNumActiveTasks(int numActiveTasks) {
		this.numActiveTasks = numActiveTasks;
	}

	public int getNumCompleteTasks() {
		return numCompleteTasks;
	}

	public void setNumCompleteTasks(int numCompleteTasks) {
		this.numCompleteTasks = numCompleteTasks;
	}

	public int getNumFailedTasks() {
		return numFailedTasks;
	}

	public void setNumFailedTasks(int numFailedTasks) {
		this.numFailedTasks = numFailedTasks;
	}

	public long getExecutorRunTime() {
		return executorRunTime;
	}

	public void setExecutorRunTime(long executorRunTime) {
		this.executorRunTime = executorRunTime;
	}

	public DateTime getSubmissionTime() {
		return submissionTime;
	}

	public void setSubmissionTime(String submissionTime) {
		DateTimeFormatter parser = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'GMT").withZoneUTC();
		this.submissionTime = parser.parseDateTime(submissionTime);
	}

	public DateTime getFirstTaskLaunchedTime() {
		return firstTaskLaunchedTime;
	}

	public void setFirstTaskLaunchedTime(String firstTaskLaunchedTime) {
		DateTimeFormatter parser = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'GMT").withZoneUTC();
		this.firstTaskLaunchedTime = parser.parseDateTime(firstTaskLaunchedTime);
	}

	public DateTime getCompletionTime() {
		return completionTime;
	}

	public void setCompletionTime(String completionTime) {
		DateTimeFormatter parser = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'GMT").withZoneUTC();;
		this.completionTime = parser.parseDateTime(completionTime);
	}

	public long getInputBytes() {
		return inputBytes;
	}

	public void setInputBytes(long inputBytes) {
		this.inputBytes = inputBytes;
	}

	public long getInputRecords() {
		return inputRecords;
	}

	public void setInputRecords(long inputRecords) {
		this.inputRecords = inputRecords;
	}

	public long getOutputBytes() {
		return outputBytes;
	}

	public void setOutputBytes(long outputBytes) {
		this.outputBytes = outputBytes;
	}

	public long getOutputRecords() {
		return outputRecords;
	}

	public void setOutputRecords(long outputRecords) {
		this.outputRecords = outputRecords;
	}

	public long getShuffleReadBytes() {
		return shuffleReadBytes;
	}

	public void setShuffleReadBytes(long shuffleReadBytes) {
		this.shuffleReadBytes = shuffleReadBytes;
	}

	public long getShuffleReadRecords() {
		return shuffleReadRecords;
	}

	public void setShuffleReadRecords(long shuffleReadRecords) {
		this.shuffleReadRecords = shuffleReadRecords;
	}

	public long getShuffleWriteBytes() {
		return shuffleWriteBytes;
	}

	public void setShuffleWriteBytes(long shuffleWriteBytes) {
		this.shuffleWriteBytes = shuffleWriteBytes;
	}

	public long getShuffleWriteRecords() {
		return shuffleWriteRecords;
	}

	public void setShuffleWriteRecords(long shuffleWriteRecords) {
		this.shuffleWriteRecords = shuffleWriteRecords;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getSchedulingPool() {
		return schedulingPool;
	}

	public void setSchedulingPool(String schedulingPool) {
		this.schedulingPool = schedulingPool;
	}

	public List<AccumulatorUpdateDTO> getAccumulatorUpdates() {
		return accumulatorUpdates;
	}

	public void setAccumulatorUpdates(List<AccumulatorUpdateDTO> accumulatorUpdates) {
		this.accumulatorUpdates = accumulatorUpdates;
	}

	public Map<String,TaskDTO> getTasks() {
		return tasks;
	}

	public void setTasks(Map<String,TaskDTO> tasks) {
		this.tasks = tasks;
	}

	public Map<String,ExecutorSummaryDTO> getExecutorSummary() {
		return executorSummary;
	}

	public void setExecutorSummary(Map<String,ExecutorSummaryDTO> executorSummary) {
		this.executorSummary = executorSummary;
	}

	public long getExecutorCpuTime() {
		return executorCpuTime;
	}

	public void setExecutorCpuTime(long executorCpuTime) {
		this.executorCpuTime = executorCpuTime;
	}
	
}
