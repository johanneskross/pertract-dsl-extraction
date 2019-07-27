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

import java.util.List;

public class TasksDTO {

	private List<TaskDTO> task;
	
	public TasksDTO(){
	}

	public List<TaskDTO> getTask() {
		return task;
	}

	public void setTask(List<TaskDTO> task) {
		this.task = task;
	}
}
