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

public class ShuffleWriteMetricsDTO {

	private long bytesWritten;
	private long writeTime;
	private long recordsWritten;
	
	public ShuffleWriteMetricsDTO(){
	}

	public long getBytesWritten() {
		return bytesWritten;
	}

	public void setBytesWritten(long bytesWritten) {
		this.bytesWritten = bytesWritten;
	}

	public long getWriteTime() {
		return writeTime;
	}

	public void setWriteTime(long writeTime) {
		this.writeTime = writeTime;
	}

	public long getRecordsWritten() {
		return recordsWritten;
	}

	public void setRecordsWritten(long recordsWritten) {
		this.recordsWritten = recordsWritten;
	}
}
