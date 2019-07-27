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

public class ComponentErrorDTO {

	private long time;
	private String errorHost;
	private String errorPort;
	private String error;
	private int errorLapsedSecs;
	private String errorWorkerLogLink;
	
	public ComponentErrorDTO() {
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getErrorHost() {
		return errorHost;
	}

	public void setErrorHost(String errorHost) {
		this.errorHost = errorHost;
	}

	public String getErrorPort() {
		return errorPort;
	}

	public void setErrorPort(String errorPort) {
		this.errorPort = errorPort;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public int getErrorLapsedSecs() {
		return errorLapsedSecs;
	}

	public void setErrorLapsedSecs(int errorLapsedSecs) {
		this.errorLapsedSecs = errorLapsedSecs;
	}

	public String getErrorWorkerLogLink() {
		return errorWorkerLogLink;
	}

	public void setErrorWorkerLogLink(String errorWorkerLogLink) {
		this.errorWorkerLogLink = errorWorkerLogLink;
	}

}
