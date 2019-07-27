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

public class StatusCountsDTO {

	@JsonProperty("CREATED") private int CREATED;
	@JsonProperty("SCHEDULED") private int SCHEDULED;
	@JsonProperty("DEPLOYING") private int DEPLOYING;
	@JsonProperty("RUNNING") private int RUNNING;
	@JsonProperty("FINISHED") private int FINISHED;
	@JsonProperty("CANCELING") private int CANCELING;
	@JsonProperty("CANCELED") private int CANCELED;
	@JsonProperty("FAILED") private int FAILED;
	
	public StatusCountsDTO() {
	}

	public int getCREATED() {
		return CREATED;
	}

	public void setCREATED(int cREATED) {
		CREATED = cREATED;
	}

	public int getSCHEDULED() {
		return SCHEDULED;
	}

	public void setSCHEDULED(int sCHEDULED) {
		SCHEDULED = sCHEDULED;
	}

	public int getDEPLOYING() {
		return DEPLOYING;
	}

	public void setDEPLOYING(int dEPLOYING) {
		DEPLOYING = dEPLOYING;
	}

	public int getRUNNING() {
		return RUNNING;
	}

	public void setRUNNING(int rUNNING) {
		RUNNING = rUNNING;
	}

	public int getFINISHED() {
		return FINISHED;
	}

	public void setFINISHED(int fINISHED) {
		FINISHED = fINISHED;
	}

	public int getCANCELING() {
		return CANCELING;
	}

	public void setCANCELING(int cANCELING) {
		CANCELING = cANCELING;
	}

	public int getCANCELED() {
		return CANCELED;
	}

	public void setCANCELED(int cANCELED) {
		CANCELED = cANCELED;
	}

	public int getFAILED() {
		return FAILED;
	}

	public void setFAILED(int fAILED) {
		FAILED = fAILED;
	}
}
