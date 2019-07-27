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

public class DetailedTopolgyDTO {

	private String id;
	private String name;
	private String uptime;
	private String status;
	private int tasksTotal;
	private int workersTotal;
	private int executorsTotal;
	private int msgTimeout;
	private String windowHint;
	private List<TopologyStatsDTO> topologyStats;
	private List<SpoutDTO> spouts;
	private List<BoltDTO> bolts;
	private String configuration;
	
	public DetailedTopolgyDTO() {
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

	public String getUptime() {
		return uptime;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTasksTotal() {
		return tasksTotal;
	}

	public void setTasksTotal(int tasksTotal) {
		this.tasksTotal = tasksTotal;
	}

	public int getWorkersTotal() {
		return workersTotal;
	}

	public void setWorkersTotal(int workersTotal) {
		this.workersTotal = workersTotal;
	}

	public int getExecutorsTotal() {
		return executorsTotal;
	}

	public void setExecutorsTotal(int executorsTotal) {
		this.executorsTotal = executorsTotal;
	}

	public int getMsgTimeout() {
		return msgTimeout;
	}

	public void setMsgTimeout(int msgTimeout) {
		this.msgTimeout = msgTimeout;
	}

	public String getWindowHint() {
		return windowHint;
	}

	public void setWindowHint(String windowHint) {
		this.windowHint = windowHint;
	}

	public List<TopologyStatsDTO> getTopologyStats() {
		return topologyStats;
	}

	public void setTopologyStats(List<TopologyStatsDTO> topologyStats) {
		this.topologyStats = topologyStats;
	}

	public List<SpoutDTO> getSpouts() {
		return spouts;
	}

	public void setSpouts(List<SpoutDTO> spouts) {
		this.spouts = spouts;
	}

	public List<BoltDTO> getBolts() {
		return bolts;
	}

	public void setBolts(List<BoltDTO> bolts) {
		this.bolts = bolts;
	}

	public String getConfiguration() {
		return configuration;
	}

	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}
	
}
