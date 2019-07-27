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
package org.fortiss.pmwt.pertract.dsl.extraction.service.repository;


import org.fortiss.pmwt.pertract.dsl.model.application.ApplicationExecutionArchitecture;
import org.fortiss.pmwt.pertract.dsl.model.dataworkload.DataWorkloadArchitecture;
import org.fortiss.pmwt.pertract.dsl.model.resources.ResourceArchitecture;

public class PerTractDSL {

	private int _id;
	private DataWorkloadArchitecture dataWorkloadArchitecture;
	private ResourceArchitecture resourceArchitecture;
	private ApplicationExecutionArchitecture applicationExecutionArchitecture;
	
	public PerTractDSL() {
		
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public DataWorkloadArchitecture getDataWorkloadArchitecture() {
		return dataWorkloadArchitecture;
	}

	public void setDataWorkloadArchitecture(DataWorkloadArchitecture dataWorkloadArchitecture) {
		this.dataWorkloadArchitecture = dataWorkloadArchitecture;
	}

	public ResourceArchitecture getResourceArchitecture() {
		return resourceArchitecture;
	}

	public void setResourceArchitecture(ResourceArchitecture resourceArchitecture) {
		this.resourceArchitecture = resourceArchitecture;
	}

	public ApplicationExecutionArchitecture getApplicationExecutionArchitecture() {
		return applicationExecutionArchitecture;
	}

	public void setApplicationExecutionArchitecture(ApplicationExecutionArchitecture applicationExecutionArchitecture) {
		this.applicationExecutionArchitecture = applicationExecutionArchitecture;
	}

}
