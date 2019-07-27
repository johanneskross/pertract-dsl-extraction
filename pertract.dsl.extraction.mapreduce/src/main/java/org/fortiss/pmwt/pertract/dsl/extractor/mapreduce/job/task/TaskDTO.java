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
package org.fortiss.pmwt.pertract.dsl.extractor.mapreduce.job.task;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class TaskDTO {
	
	private String id;
	private String state;
	private String type;
	private String successfulAttempt;
	private float progress;
	private DateTime startTime;
	private DateTime finishTime;
	private long elapsedTime;
	
	public TaskDTO(){
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSuccessfulAttempt() {
		return successfulAttempt;
	}

	public void setSuccessfulAttempt(String successfulAttempt) {
		this.successfulAttempt = successfulAttempt;
	}

	public float getProgress() {
		return progress;
	}

	public void setProgress(float progress) {
		this.progress = progress;
	}

	public DateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = new DateTime(startTime, DateTimeZone.UTC);
	}

	public DateTime getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(long finishTime) {
		this.finishTime = new DateTime(startTime, DateTimeZone.UTC);
	}

	public long getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	
}
