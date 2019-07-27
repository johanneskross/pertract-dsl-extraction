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

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ClusterSummaryDTO {

	private String stormVersion;
	private String nimbusUptime;
	private int supervisors;
	private int topologies;
	private int slotsTotal;
	private int slotsUsed;
	private int slotsFree;
	private int executorsTotal;
	private int tasksTotal;
	
	@JsonIgnore
	private String user;
	

	public ClusterSummaryDTO() {
	}

	public String getStormVersion() {
		return stormVersion;
	}

	public void setStormVersion(String stormVersion) {
		this.stormVersion = stormVersion;
	}

	public String getNimbusUptime() {
		return nimbusUptime;
	}

	public void setNimbusUptime(String nimbusUptime) {
		this.nimbusUptime = nimbusUptime;
	}

	public int getSupervisors() {
		return supervisors;
	}

	public void setSupervisors(int supervisors) {
		this.supervisors = supervisors;
	}

	public int getTopologies() {
		return topologies;
	}

	public void setTopologies(int topologies) {
		this.topologies = topologies;
	}

	public int getSlotsTotal() {
		return slotsTotal;
	}

	public void setSlotsTotal(int slotsTotal) {
		this.slotsTotal = slotsTotal;
	}

	public int getSlotsUsed() {
		return slotsUsed;
	}

	public void setSlotsUsed(int slotsUsed) {
		this.slotsUsed = slotsUsed;
	}

	public int getSlotsFree() {
		return slotsFree;
	}

	public void setSlotsFree(int slotsFree) {
		this.slotsFree = slotsFree;
	}

	public int getExecutorsTotal() {
		return executorsTotal;
	}

	public void setExecutorsTotal(int executorsTotal) {
		this.executorsTotal = executorsTotal;
	}

	public int getTasksTotal() {
		return tasksTotal;
	}

	public void setTasksTotal(int tasksTotal) {
		this.tasksTotal = tasksTotal;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
