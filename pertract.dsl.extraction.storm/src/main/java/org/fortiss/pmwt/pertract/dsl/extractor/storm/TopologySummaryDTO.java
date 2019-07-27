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
package org.fortiss.pmwt.pertract.dsl.extractor.storm;

import java.util.List;

public class TopologySummaryDTO {
	
	private List<TopologyDTO> topologies;
	
	public TopologySummaryDTO() {
	}

	public List<TopologyDTO> getTopologies() {
		return topologies;
	}

	public void setTopologies(List<TopologyDTO> topologies) {
		this.topologies = topologies;
	}
}
