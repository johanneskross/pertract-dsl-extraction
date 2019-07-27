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

public class TasksDTO {
	
	@JsonProperty("CREATED") private long CREATED;
	@JsonProperty("SCHEDULED") private long SCHEDULED;
	@JsonProperty("DEPLOYING") private long DEPLOYING;
	@JsonProperty("RUNNING") private long RUNNING;
	@JsonProperty("FINISHED") private long FINISHED;
	@JsonProperty("CANCELING") private long CANCELING;
	@JsonProperty("CANCELED") private long CANCELED;
	@JsonProperty("FAILED") private long FAILED;
	
	public TasksDTO() {
	}

	public long getCREATED() {
		return CREATED;
	}

	public void setCREATED(long cREATED) {
		CREATED = cREATED;
	}

	public long getSCHEDULED() {
		return SCHEDULED;
	}

	public void setSCHEDULED(long sCHEDULED) {
		SCHEDULED = sCHEDULED;
	}

	public long getDEPLOYING() {
		return DEPLOYING;
	}

	public void setDEPLOYING(long dEPLOYING) {
		DEPLOYING = dEPLOYING;
	}

	public long getRUNNING() {
		return RUNNING;
	}

	public void setRUNNING(long rUNNING) {
		RUNNING = rUNNING;
	}

	public long getFINISHED() {
		return FINISHED;
	}

	public void setFINISHED(long fINISHED) {
		FINISHED = fINISHED;
	}

	public long getCANCELING() {
		return CANCELING;
	}

	public void setCANCELING(long cANCELING) {
		CANCELING = cANCELING;
	}

	public long getCANCELED() {
		return CANCELED;
	}

	public void setCANCELED(long cANCELED) {
		CANCELED = cANCELED;
	}

	public long getFAILED() {
		return FAILED;
	}

	public void setFAILED(long fAILED) {
		FAILED = fAILED;
	}
	
}
