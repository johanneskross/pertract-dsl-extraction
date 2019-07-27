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
package org.fortiss.pmwt.pertract.dsl.extractor.mapreduce.job.task.attempt;

import java.util.List;

public class TaskAttemptsDTO {

	private List<TaskAttemptDTO> taskAttempt;
	
	public TaskAttemptsDTO() {
	}

	public List<TaskAttemptDTO> getTaskAttempt() {
		return taskAttempt;
	}

	public void setTaskAttempt(List<TaskAttemptDTO> taskAttempt) {
		this.taskAttempt = taskAttempt;
	}
}
