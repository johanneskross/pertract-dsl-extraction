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

@JsonIgnoreProperties(value={"configuration", "visualizationTable"})
public class TopologyDTO {
	
	private String id;
	private String encodedId;
	private String name;
	private String owner;
	private String status;
	private String uptime;
	private String schedulerInfo;
	private String window;
	private String windowHint;
	private String user;
	private int msgTimeout;
	private int tasksTotal;
	private int workersTotal;
	private int executorsTotal;
	private int replicationCount;
	private SpoutDTO[] spouts;
	private BoltDTO[] bolts;
	private TopologyStatsDTO[] topologyStats;
	
	public TopologyDTO() {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUptime() {
		return uptime;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

	public String getSchedulerInfo() {
		return schedulerInfo;
	}

	public void setSchedulerInfo(String schedulerInfo) {
		this.schedulerInfo = schedulerInfo;
	}

	public String getWindow() {
		return window;
	}

	public void setWindow(String window) {
		this.window = window;
	}

	public String getWindowHint() {
		return windowHint;
	}

	public void setWindowHint(String windowHint) {
		this.windowHint = windowHint;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getMsgTimeout() {
		return msgTimeout;
	}

	public void setMsgTimeout(int msgTimeout) {
		this.msgTimeout = msgTimeout;
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

	public int getReplicationCount() {
		return replicationCount;
	}

	public void setReplicationCount(int replicationCount) {
		this.replicationCount = replicationCount;
	}

	public SpoutDTO[] getSpouts() {
		return spouts;
	}

	public void setSpouts(SpoutDTO[] spouts) {
		this.spouts = spouts;
	}

	public BoltDTO[] getBolts() {
		return bolts;
	}

	public void setBolts(BoltDTO[] bolts) {
		this.bolts = bolts;
	}

	public TopologyStatsDTO[] getTopologyStats() {
		return topologyStats;
	}

	public void setTopologyStats(TopologyStatsDTO[] topologyStats) {
		this.topologyStats = topologyStats;
	}
	
}
