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
package org.fortiss.pmwt.pertract.dsl.extractor.storm;

public class BoltStatsDTO {

	private String windowPretty;
	private String window;
	private long transferred;
	private long emitted;
	private long executed;
	private String processLatency;
	private String executeLatency;
	private long acked;
	private long failed;
	
	public BoltStatsDTO() {
	}

	public String getWindowPretty() {
		return windowPretty;
	}

	public void setWindowPretty(String windowPretty) {
		this.windowPretty = windowPretty;
	}

	public String getWindow() {
		return window;
	}

	public void setWindow(String window) {
		this.window = window;
	}

	public long getTransferred() {
		return transferred;
	}

	public void setTransferred(long transferred) {
		this.transferred = transferred;
	}

	public long getEmitted() {
		return emitted;
	}

	public void setEmitted(long emitted) {
		this.emitted = emitted;
	}

	public long getExecuted() {
		return executed;
	}

	public void setExecuted(long executed) {
		this.executed = executed;
	}

	public String getProcessLatency() {
		return processLatency;
	}

	public void setProcessLatency(String processLatency) {
		this.processLatency = processLatency;
	}

	public String getExecuteLatency() {
		return executeLatency;
	}

	public void setExecuteLatency(String executeLatency) {
		this.executeLatency = executeLatency;
	}

	public long getAcked() {
		return acked;
	}

	public void setAcked(long acked) {
		this.acked = acked;
	}

	public long getFailed() {
		return failed;
	}

	public void setFailed(long failed) {
		this.failed = failed;
	}
}
