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
package org.fortiss.pmwt.pertract.dsl.extractor.flink.jobs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MetricsDTO {

	@JsonProperty("read-bytes") private long readBytes;
	@JsonProperty("write-bytes") private long writeBytes;
	@JsonProperty("read-records") private long readRecords;
	@JsonProperty("write-records") private long writeRecords;
	
	public MetricsDTO() {
	}
	
	public long getReadBytes() {
		return readBytes;
	}

	public void setReadBytes(long readBytes) {
		this.readBytes = readBytes;
	}

	public long getWriteBytes() {
		return writeBytes;
	}

	public void setWriteBytes(long writeBytes) {
		this.writeBytes = writeBytes;
	}

	public long getReadRecords() {
		return readRecords;
	}

	public void setReadRecords(long readRecords) {
		this.readRecords = readRecords;
	}

	public long getWriteRecords() {
		return writeRecords;
	}

	public void setWriteRecords(long writeRecords) {
		this.writeRecords = writeRecords;
	}

}
