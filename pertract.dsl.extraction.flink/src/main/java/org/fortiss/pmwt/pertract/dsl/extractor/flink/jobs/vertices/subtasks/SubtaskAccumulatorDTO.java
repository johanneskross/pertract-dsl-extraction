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
package org.fortiss.pmwt.pertract.dsl.extractor.flink.jobs.vertices.subtasks;

import java.util.List;

import org.fortiss.pmwt.pertract.dsl.extractor.flink.jobs.vertices.AccumulatorDTO;

public class SubtaskAccumulatorDTO {

	private int subtask;
	private int attempt;
	private String host;
	private List<AccumulatorDTO> userAccumulators;
	
	public SubtaskAccumulatorDTO() {
	}

	public int getSubtask() {
		return subtask;
	}

	public void setSubtask(int subtask) {
		this.subtask = subtask;
	}

	public int getAttempt() {
		return attempt;
	}

	public void setAttempt(int attempt) {
		this.attempt = attempt;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public List<AccumulatorDTO> getUserAccumulators() {
		return userAccumulators;
	}

	public void setUserAccumulators(List<AccumulatorDTO> userAccumulators) {
		this.userAccumulators = userAccumulators;
	}
}
