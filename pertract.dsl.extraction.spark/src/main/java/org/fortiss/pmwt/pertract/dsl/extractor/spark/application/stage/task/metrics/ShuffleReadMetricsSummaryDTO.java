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

public class ShuffleReadMetricsSummaryDTO {
	
	private long[] readBytes;
	private long[] readRecords;
	private long[] remoteBlocksFetched;
	private long[] localBlocksFetched;
	private long[] fetchWaitTime;
	private long[] remoteBytesRead;
	private long[] totalBlocksFetched;
	
	public ShuffleReadMetricsSummaryDTO(){
	}

	public long[] getReadBytes() {
		return readBytes;
	}

	public void setReadBytes(long[] readBytes) {
		this.readBytes = readBytes;
	}

	public long[] getReadRecords() {
		return readRecords;
	}

	public void setReadRecords(long[] readRecords) {
		this.readRecords = readRecords;
	}

	public long[] getRemoteBlocksFetched() {
		return remoteBlocksFetched;
	}

	public void setRemoteBlocksFetched(long[] remoteBlocksFetched) {
		this.remoteBlocksFetched = remoteBlocksFetched;
	}

	public long[] getLocalBlocksFetched() {
		return localBlocksFetched;
	}

	public void setLocalBlocksFetched(long[] localBlocksFetched) {
		this.localBlocksFetched = localBlocksFetched;
	}

	public long[] getFetchWaitTime() {
		return fetchWaitTime;
	}

	public void setFetchWaitTime(long[] fetchWaitTime) {
		this.fetchWaitTime = fetchWaitTime;
	}

	public long[] getRemoteBytesRead() {
		return remoteBytesRead;
	}

	public void setRemoteBytesRead(long[] remoteBytesRead) {
		this.remoteBytesRead = remoteBytesRead;
	}

	public long[] getTotalBlocksFetched() {
		return totalBlocksFetched;
	}

	public void setTotalBlocksFetched(long[] totalBlocksFetched) {
		this.totalBlocksFetched = totalBlocksFetched;
	}
}
