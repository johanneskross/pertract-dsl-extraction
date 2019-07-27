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
package org.fortiss.pmwt.pertract.dsl.extractor.flink.jobs.vertices;

import java.util.List;

public class VertexAccumulatorsDTO {

	private String id;
	private List<AccumulatorDTO> userAccumulators;
	
	public VertexAccumulatorsDTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<AccumulatorDTO> getUserAccumulators() {
		return userAccumulators;
	}

	public void setUserAccumulators(List<AccumulatorDTO> userAccumulators) {
		this.userAccumulators = userAccumulators;
	}
}
