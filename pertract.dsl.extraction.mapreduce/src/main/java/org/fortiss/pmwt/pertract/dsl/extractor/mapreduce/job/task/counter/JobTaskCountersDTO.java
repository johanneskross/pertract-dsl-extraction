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
package org.fortiss.pmwt.pertract.dsl.extractor.mapreduce.job.task.counter;

import java.util.List;

public class JobTaskCountersDTO {
	
	private String id;
	private List<JobTaskCounterGroupDTO> taskCounterGroup;
	
	public JobTaskCountersDTO(){
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<JobTaskCounterGroupDTO> getTaskCounterGroup() {
		return taskCounterGroup;
	}

	public void setTaskcounterGroup(List<JobTaskCounterGroupDTO> taskCounterGroup) {
		this.taskCounterGroup = taskCounterGroup;
	}

}
