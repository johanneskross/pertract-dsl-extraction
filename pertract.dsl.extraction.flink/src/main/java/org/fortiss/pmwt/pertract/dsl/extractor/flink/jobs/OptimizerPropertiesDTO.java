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

import java.util.List;

public class OptimizerPropertiesDTO {

	private List<PropertyDTO> global_properties;
	private List<PropertyDTO> local_properties;
	private List<PropertyDTO> estimates;
	private List<PropertyDTO> costs;
	private List<PropertyDTO> compiler_hints;
	
	public OptimizerPropertiesDTO() {
	}

	public List<PropertyDTO> getGlobal_properties() {
		return global_properties;
	}

	public void setGlobal_properties(List<PropertyDTO> global_properties) {
		this.global_properties = global_properties;
	}

	public List<PropertyDTO> getLocal_properties() {
		return local_properties;
	}

	public void setLocal_properties(List<PropertyDTO> local_properties) {
		this.local_properties = local_properties;
	}

	public List<PropertyDTO> getEstimates() {
		return estimates;
	}

	public void setEstimates(List<PropertyDTO> estimates) {
		this.estimates = estimates;
	}

	public List<PropertyDTO> getCosts() {
		return costs;
	}

	public void setCosts(List<PropertyDTO> costs) {
		this.costs = costs;
	}

	public List<PropertyDTO> getCompiler_hints() {
		return compiler_hints;
	}

	public void setCompiler_hints(List<PropertyDTO> compiler_hints) {
		this.compiler_hints = compiler_hints;
	}
}
