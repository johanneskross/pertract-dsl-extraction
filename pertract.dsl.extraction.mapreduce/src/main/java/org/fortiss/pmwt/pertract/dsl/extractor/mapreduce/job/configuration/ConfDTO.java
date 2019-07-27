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
package org.fortiss.pmwt.pertract.dsl.extractor.mapreduce.job.configuration;

import java.util.List;

public class ConfDTO {

	private String path;
	private List<PropertyDTO> property;
	
	public ConfDTO(){
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<PropertyDTO> getProperty() {
		return property;
	}

	public void setProperty(List<PropertyDTO> property) {
		this.property = property;
	}
	
}
