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
package org.fortiss.pmwt.pertract.dsl.extractor.mapreduce.job;

import java.util.ArrayList;

public class JobsDTO {
	ArrayList<JobDTO> job;

	public JobsDTO() {
	}

	public ArrayList<JobDTO> getJob() {
		return job;
	}

	public void setJob(ArrayList<JobDTO> job) {
		this.job = job;
	}

}
