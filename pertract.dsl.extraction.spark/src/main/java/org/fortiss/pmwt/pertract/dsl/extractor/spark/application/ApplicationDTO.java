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
package org.fortiss.pmwt.pertract.dsl.extractor.spark.application;

import java.util.List;

public class ApplicationDTO {

	private String id;
	private String name;
	private List<AttemptDTO> attempts;
	
	public ApplicationDTO(){
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AttemptDTO> getAttempts() {
		return attempts;
	}

	public void setAttempts(List<AttemptDTO> attempts) {
		this.attempts = attempts;
	}
}
