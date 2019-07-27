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
package org.fortiss.pmwt.pertract.dsl.extractor.spark.application.stage.task;

import org.fortiss.pmwt.pertract.dsl.extractor.spark.application.stage.task.metrics.InputMetricsSummaryDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.spark.application.stage.task.metrics.OutputMetricsSummaryDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.spark.application.stage.task.metrics.ShuffleReadMetricsSummaryDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.spark.application.stage.task.metrics.ShuffleWriteMetricsSummaryDTO;

public class TaskSummaryDTO {
	
	private double[] quantiles;
	private double[] executorDeserializeTime;
	private double[] executorRunTime;
	private double[] resultSize;
	private double[] jvmGcTime;
	private double[] resultSerializationTime;
	private double[] memoryBytesSpilled;
	private double[] diskBytesSpilled;
	private double[] executorDeserializeCpuTime;
	private double[] executorCpuTime;
	private InputMetricsSummaryDTO inputMetrics;
	private OutputMetricsSummaryDTO outputMetrics;
	private ShuffleReadMetricsSummaryDTO shuffleReadMetrics;
	private ShuffleWriteMetricsSummaryDTO shuffleWriteMetrics;
	
	public TaskSummaryDTO(){
	}
	
	public double[] getQuantiles() {
		return quantiles;
	}

	public void setQuantiles(double[] quantiles) {
		this.quantiles = quantiles;
	}


	public double[] getExecutorDeserializeTime() {
		return executorDeserializeTime;
	}


	public void setExecutorDeserializeTime(double[] executorDeserializeTime) {
		this.executorDeserializeTime = executorDeserializeTime;
	}


	public double[] getExecutorRunTime() {
		return executorRunTime;
	}


	public void setExecutorRunTime(double[] executorRunTime) {
		this.executorRunTime = executorRunTime;
	}


	public double[] getResultSize() {
		return resultSize;
	}


	public void setResultSize(double[] resultSize) {
		this.resultSize = resultSize;
	}


	public double[] getJvmGcTime() {
		return jvmGcTime;
	}


	public void setJvmGcTime(double[] jvmGcTime) {
		this.jvmGcTime = jvmGcTime;
	}


	public double[] getResultSerializationTime() {
		return resultSerializationTime;
	}


	public void setResultSerializationTime(double[] resultSerializationTime) {
		this.resultSerializationTime = resultSerializationTime;
	}


	public double[] getMemoryBytesSpilled() {
		return memoryBytesSpilled;
	}


	public void setMemoryBytesSpilled(double[] memoryBytesSpilled) {
		this.memoryBytesSpilled = memoryBytesSpilled;
	}


	public double[] getDiskBytesSpilled() {
		return diskBytesSpilled;
	}


	public void setDiskBytesSpilled(double[] diskBytesSpilled) {
		this.diskBytesSpilled = diskBytesSpilled;
	}


	public double[] getExecutorDeserializeCpuTime() {
		return executorDeserializeCpuTime;
	}

	public void setExecutorDeserializeCpuTime(double[] executorDeserializeCpuTime) {
		this.executorDeserializeCpuTime = executorDeserializeCpuTime;
	}

	public double[] getExecutorCpuTime() {
		return executorCpuTime;
	}

	public void setExecutorCpuTime(double[] executorCpuTime) {
		this.executorCpuTime = executorCpuTime;
	}

	public InputMetricsSummaryDTO getInputMetrics() {
		return inputMetrics;
	}


	public void setInputMetrics(InputMetricsSummaryDTO inputMetrics) {
		this.inputMetrics = inputMetrics;
	}


	public OutputMetricsSummaryDTO getOutputMetrics() {
		return outputMetrics;
	}


	public void setOutputMetrics(OutputMetricsSummaryDTO outputMetrics) {
		this.outputMetrics = outputMetrics;
	}


	public ShuffleReadMetricsSummaryDTO getShuffleReadMetrics() {
		return shuffleReadMetrics;
	}


	public void setShuffleMetrics(ShuffleReadMetricsSummaryDTO shuffleReadMetrics) {
		this.shuffleReadMetrics = shuffleReadMetrics;
	}


	public ShuffleWriteMetricsSummaryDTO getShuffleWriteMetrics() {
		return shuffleWriteMetrics;
	}


	public void setShuffleWriteMetrics(ShuffleWriteMetricsSummaryDTO shuffleWriteMetrics) {
		this.shuffleWriteMetrics = shuffleWriteMetrics;
	}
}
