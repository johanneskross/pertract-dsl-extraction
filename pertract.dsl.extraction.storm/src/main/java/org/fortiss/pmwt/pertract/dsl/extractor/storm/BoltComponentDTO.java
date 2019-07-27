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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value={"executorStats", "inputStats", "outputStats"})
public class BoltComponentDTO {
	
	private String id;
	private String encodedTopologyId;
	private String encodedId;
	private String name;
	private String componentType;
	private String windowHint;
	private int executors;
	private String topologyId;
	private int tasks;
	private String window;
	private String user;
	private ComponentErrorDTO[] componentErrors;
	private BoltStatsDTO[] boltStats;
	
	public BoltComponentDTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEncodedId() {
		return encodedId;
	}

	public void setEncodedId(String encodedId) {
		this.encodedId = encodedId;
	}

	public String getEncodedTopologyId() {
		return encodedTopologyId;
	}

	public void setEncodedTopologyId(String encodedTopologyId) {
		this.encodedTopologyId = encodedTopologyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComponentType() {
		return componentType;
	}

	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}

	public String getWindowHint() {
		return windowHint;
	}

	public void setWindowHint(String windowHint) {
		this.windowHint = windowHint;
	}

	public int getExecutors() {
		return executors;
	}

	public void setExecutors(int executors) {
		this.executors = executors;
	}

	public String getTopologyId() {
		return topologyId;
	}

	public void setTopologyId(String topologyId) {
		this.topologyId = topologyId;
	}

	public int getTasks() {
		return tasks;
	}

	public void setTasks(int tasks) {
		this.tasks = tasks;
	}

	public String getWindow() {
		return window;
	}

	public void setWindow(String window) {
		this.window = window;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public ComponentErrorDTO[] getComponentErrors() {
		return componentErrors;
	}

	public void setComponentErrors(ComponentErrorDTO[] componentErrors) {
		this.componentErrors = componentErrors;
	}

	public BoltStatsDTO[] getBoltStats() {
		return boltStats;
	}

	public void setBoltStats(BoltStatsDTO[] boltStats) {
		this.boltStats = boltStats;
	}
}
