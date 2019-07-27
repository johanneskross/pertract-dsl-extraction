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

public class SpoutSummaryDTO {

	private String windowPretty;
	private String window;
	private long emitted;
	private long transferred;
	private String completeLatency;
	private long acked;
	private long failed;
	
	public SpoutSummaryDTO() {
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

	public long getEmitted() {
		return emitted;
	}

	public void setEmitted(long emitted) {
		this.emitted = emitted;
	}

	public long getTransferred() {
		return transferred;
	}

	public void setTransferred(long transferred) {
		this.transferred = transferred;
	}

	public String getCompleteLatency() {
		return completeLatency;
	}

	public void setCompleteLatency(String completeLatency) {
		this.completeLatency = completeLatency;
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
