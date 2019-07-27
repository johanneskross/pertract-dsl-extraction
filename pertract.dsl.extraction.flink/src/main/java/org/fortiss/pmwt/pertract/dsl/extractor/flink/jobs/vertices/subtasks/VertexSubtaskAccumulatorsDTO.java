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
package org.fortiss.pmwt.pertract.dsl.extractor.flink.jobs.vertices.subtasks;

import java.util.List;

public class VertexSubtaskAccumulatorsDTO {

	private String id;
	private int parallelism;
	private List<SubtaskAccumulatorDTO> subtasks;
	
	
	
}
