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
package org.fortiss.pmwt.pertract.dsl.extractor.spark.application.stage.task.metrics;

public class TaskMetricsDTO {
	private long executorDeserializeTime;
	private long executorDeserializeCpuTime;
	private long executorRunTime;
	private long executorCpuTime;
	private long resultSize;
	private long jvmGcTime;
	private long resultSerializationTime;
	private long memoryBytesSpilled;
	private long diskBytesSpilled;
	private InputMetricsDTO inputMetrics;
	private OutputMetricsDTO outputMetrics;
	private ShuffleReadMetricsDTO shuffleReadMetrics;
	private ShuffleWriteMetricsDTO shuffleWriteMetrics;
	
	public TaskMetricsDTO(){
	}

	public long getExecutorDeserializeTime() {
		return executorDeserializeTime;
	}

	public void setExecutorDeserializeTime(long executorDeserializeTime) {
		this.executorDeserializeTime = executorDeserializeTime;
	}

	public long getExecutorDeserializeCpuTime() {
		return executorDeserializeCpuTime;
	}

	public void setExecutorDeserializeCpuTime(long executorDeserializeCpuTime) {
		this.executorDeserializeCpuTime = executorDeserializeCpuTime;
	}

	public long getExecutorRunTime() {
		return executorRunTime;
	}

	public void setExecutorRunTime(long executorRunTime) {
		this.executorRunTime = executorRunTime;
	}

	public long getExecutorCpuTime() {
		return executorCpuTime;
	}

	public void setExecutorCpuTime(long executorCpuTime) {
		this.executorCpuTime = executorCpuTime;
	}

	public long getResultSize() {
		return resultSize;
	}

	public void setResultSize(long resultSize) {
		this.resultSize = resultSize;
	}

	public long getJvmGcTime() {
		return jvmGcTime;
	}

	public void setJvmGcTime(long jvmGcTime) {
		this.jvmGcTime = jvmGcTime;
	}

	public long getResultSerializationTime() {
		return resultSerializationTime;
	}

	public void setResultSerializationTime(long resultSerializationTime) {
		this.resultSerializationTime = resultSerializationTime;
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

	public InputMetricsDTO getInputMetrics() {
		return inputMetrics;
	}

	public void setInputMetrics(InputMetricsDTO inputMetrics) {
		this.inputMetrics = inputMetrics;
	}

	public OutputMetricsDTO getOutputMetrics() {
		return outputMetrics;
	}

	public void setOutputMetrics(OutputMetricsDTO outputMetrics) {
		this.outputMetrics = outputMetrics;
	}

	public ShuffleReadMetricsDTO getShuffleReadMetrics() {
		return shuffleReadMetrics;
	}

	public void setShuffleReadMetrics(ShuffleReadMetricsDTO shuffleReadMetrics) {
		this.shuffleReadMetrics = shuffleReadMetrics;
	}

	public ShuffleWriteMetricsDTO getShuffleWriteMetrics() {
		return shuffleWriteMetrics;
	}

	public void setShuffleWriteMetrics(ShuffleWriteMetricsDTO shuffleWriteMetrics) {
		this.shuffleWriteMetrics = shuffleWriteMetrics;
	}
}
