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

public class JobTaskAttemptCountersDTO {
	
	private String id;
	private List<JobTaskAttemptCounterGroupDTO> taskAttemptCounterGroup;
	
	public JobTaskAttemptCountersDTO(){
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<JobTaskAttemptCounterGroupDTO> getTaskAttemptCounterGroup() {
		return taskAttemptCounterGroup;
	}

	public void setTaskAttemptCounterGroup(List<JobTaskAttemptCounterGroupDTO> taskAttemptCounterGroup) {
		this.taskAttemptCounterGroup = taskAttemptCounterGroup;
	}

}
