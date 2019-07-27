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

public class InputMetricsSummaryDTO {
	
	public long[] bytesRead;
	public long[] recordsRead;
	
	public InputMetricsSummaryDTO(){
	}

	public long[] getBytesRead() {
		return bytesRead;
	}

	public void setBytesRead(long[] bytesRead) {
		this.bytesRead = bytesRead;
	}

	public long[] getRecordsRead() {
		return recordsRead;
	}

	public void setRecordsRead(long[] recordsRead) {
		this.recordsRead = recordsRead;
	}
}
