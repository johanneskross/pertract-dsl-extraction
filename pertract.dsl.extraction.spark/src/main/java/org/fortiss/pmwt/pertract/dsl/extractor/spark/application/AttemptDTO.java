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
package org.fortiss.pmwt.pertract.dsl.extractor.spark.application;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@JsonIgnoreProperties(value={"lastUpdated", "duration", "lastUpdatedEpoch", "startTimeEpoch", "endTimeEpoch"})
public class AttemptDTO {

	private DateTime startTime;
	private DateTime endTime;
	private String sparkUser;
	private boolean completed;
	
	public AttemptDTO(){
	}

	public DateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		DateTimeFormatter parser = DateTimeFormat.forPattern(SparkDAO.DATE_TIME_FORMAT);
		this.startTime = parser.parseDateTime(startTime);
	}

	public DateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		DateTimeFormatter parser = DateTimeFormat.forPattern(SparkDAO.DATE_TIME_FORMAT);
		this.endTime = parser.parseDateTime(endTime);
	}

	public String getSparkUser() {
		return sparkUser;
	}

	public void setSparkUser(String sparkUser) {
		this.sparkUser = sparkUser;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
}
