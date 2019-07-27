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

public class ShuffleReadMetricsDTO {

	private long remoteBlocksFetched;
	private long localBlocksFetched;
	private long fetchWaitTime;
	private long remoteBytesRead;
	private long totalBlocksFetched;
	private long localBytesRead;
	private long recordsRead;
	
	public long getRemoteBlocksFetched() {
		return remoteBlocksFetched;
	}

	public void setRemoteBlocksFetched(long remoteBlocksFetched) {
		this.remoteBlocksFetched = remoteBlocksFetched;
	}

	public long getLocalBlocksFetched() {
		return localBlocksFetched;
	}

	public void setLocalBlocksFetched(long localBlocksFetched) {
		this.localBlocksFetched = localBlocksFetched;
	}

	public long getFetchWaitTime() {
		return fetchWaitTime;
	}

	public void setFetchWaitTime(long fetchWaitTime) {
		this.fetchWaitTime = fetchWaitTime;
	}

	public long getRemoteBytesRead() {
		return remoteBytesRead;
	}

	public void setRemoteBytesRead(long remoteBytesRead) {
		this.remoteBytesRead = remoteBytesRead;
	}

	public long getTotalBlocksFetched() {
		return totalBlocksFetched;
	}

	public void setTotalBlocksFetched(long totalBlocksFetched) {
		this.totalBlocksFetched = totalBlocksFetched;
	}

	public long getLocalBytesRead() {
		return localBytesRead;
	}

	public void setLocalBytesRead(long localBytesRead) {
		this.localBytesRead = localBytesRead;
	}

	public long getRecordsRead() {
		return recordsRead;
	}

	public void setRecordsRead(long recordsRead) {
		this.recordsRead = recordsRead;
	}

	public ShuffleReadMetricsDTO(){
	}
}
