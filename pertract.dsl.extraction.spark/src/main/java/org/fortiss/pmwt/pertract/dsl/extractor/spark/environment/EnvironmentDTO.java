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
package org.fortiss.pmwt.pertract.dsl.extractor.spark.environment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value={"runtime", "systemProperties", "classpathEntries"})
public class EnvironmentDTO {

	private Map<String, String> sparkProperties;

	public void setSparkProperties(List<List<String>> sparkProperties) {
		this.sparkProperties = new HashMap<String,String>();
		for (List<String> property : sparkProperties) {
			this.sparkProperties.put(property.get(0), property.get(1));
		}
	}
	
	public Map<String,String> getSparkProperties() {
		return this.sparkProperties;
	}
	
}
