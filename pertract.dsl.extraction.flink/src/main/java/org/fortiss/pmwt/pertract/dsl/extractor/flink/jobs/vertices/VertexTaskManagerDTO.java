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

public class VertexTaskManagerDTO {
	
	private String id;
	private String name;
	private long now;
	private List<TaskManagerDTO> taskmanagers;
	
	public VertexTaskManagerDTO() {
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

	public long getNow() {
		return now;
	}

	public void setNow(long now) {
		this.now = now;
	}

	public List<TaskManagerDTO> getTaskmanagers() {
		return taskmanagers;
	}

	public void setTaskmanagers(List<TaskManagerDTO> taskmanagers) {
		this.taskmanagers = taskmanagers;
	}	
}
