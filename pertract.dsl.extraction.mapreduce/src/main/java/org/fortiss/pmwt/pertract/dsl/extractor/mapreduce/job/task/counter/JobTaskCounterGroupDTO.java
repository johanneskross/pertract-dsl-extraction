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

public class JobTaskCounterGroupDTO {

	private String counterGroupName;
	private List<JobTaskCounterDTO> counter;
	
	public JobTaskCounterGroupDTO(){
	}

	public String getCounterGroupName() {
		return counterGroupName;
	}

	public void setCounterGroupName(String counterGroupName) {
		this.counterGroupName = counterGroupName;
	}

	public List<JobTaskCounterDTO> getCounter() {
		return counter;
	}

	public void setCounter(List<JobTaskCounterDTO> counter) {
		this.counter = counter;
	}
	
}
