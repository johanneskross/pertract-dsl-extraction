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

public class ShuffleWriteMetricsSummaryDTO {
	
	public long[] writeBytes;
	public long[] writeRecords;
	public long[] writeTime;
	
	public ShuffleWriteMetricsSummaryDTO(){
	}

	public long[] getWriteBytes() {
		return writeBytes;
	}

	public void setWriteBytes(long[] writeBytes) {
		this.writeBytes = writeBytes;
	}

	public long[] getWriteRecords() {
		return writeRecords;
	}

	public void setWriteRecords(long[] writeRecords) {
		this.writeRecords = writeRecords;
	}

	public long[] getWriteTime() {
		return writeTime;
	}

	public void setWriteTime(long[] writeTime) {
		this.writeTime = writeTime;
	}
}
