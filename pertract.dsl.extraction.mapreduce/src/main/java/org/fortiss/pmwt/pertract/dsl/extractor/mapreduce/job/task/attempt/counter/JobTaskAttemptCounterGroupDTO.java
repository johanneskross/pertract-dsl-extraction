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
package org.fortiss.pmwt.pertract.dsl.extractor.mapreduce.job.task.attempt.counter;

import java.util.List;

public class JobTaskAttemptCounterGroupDTO {

	private String counterGroupName;
	private List<JobTaskAttemptCounterDTO> counter;
	
	public JobTaskAttemptCounterGroupDTO(){
	}

	public String getCounterGroupName() {
		return counterGroupName;
	}

	public void setCounterGroupName(String counterGroupName) {
		this.counterGroupName = counterGroupName;
	}

	public List<JobTaskAttemptCounterDTO> getCounter() {
		return counter;
	}

	public void setCounter(List<JobTaskAttemptCounterDTO> counter) {
		this.counter = counter;
	}
	
}
