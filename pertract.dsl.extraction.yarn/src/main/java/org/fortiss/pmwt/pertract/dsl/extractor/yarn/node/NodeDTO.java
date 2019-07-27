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
package org.fortiss.pmwt.pertract.dsl.extractor.yarn.node;

public class NodeDTO {

	private String rack;
	private String state;
	private String id;
	private String nodeHostName;
	private String nodeHTTPAddress;
	private String healthReport;
	private String version;
	private long lastHealthUpdate;
	private long usedMemoryMB;
	private long availMemoryMB;
	private long usedVirtualCores;
	private long availableVirtualCores;
	private int numContainers;

	public NodeDTO() {
	}

	public String getRack() {
		return rack;
	}

	public void setRack(String rack) {
		this.rack = rack;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNodeHostName() {
		return nodeHostName;
	}

	public void setNodeHostName(String nodeHostName) {
		this.nodeHostName = nodeHostName;
	}

	public String getNodeHTTPAddress() {
		return nodeHTTPAddress;
	}

	public void setNodeHTTPAddress(String nodeHTTPAddress) {
		this.nodeHTTPAddress = nodeHTTPAddress;
	}

	public String getHealthReport() {
		return healthReport;
	}

	public void setHealthReport(String healthReport) {
		this.healthReport = healthReport;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public long getLastHealthUpdate() {
		return lastHealthUpdate;
	}

	public void setLastHealthUpdate(long lastHealthUpdate) {
		this.lastHealthUpdate = lastHealthUpdate;
	}

	public long getUsedMemoryMB() {
		return usedMemoryMB;
	}

	public void setUsedMemoryMB(long usedMemoryMB) {
		this.usedMemoryMB = usedMemoryMB;
	}

	public long getAvailMemoryMB() {
		return availMemoryMB;
	}

	public void setAvailMemoryMB(long availMemoryMB) {
		this.availMemoryMB = availMemoryMB;
	}

	public long getUsedVirtualCores() {
		return usedVirtualCores;
	}

	public void setUsedVirtualCores(long usedVirtualCores) {
		this.usedVirtualCores = usedVirtualCores;
	}

	public long getAvailableVirtualCores() {
		return availableVirtualCores;
	}

	public void setAvailableVirtualCores(long availableVirtualCores) {
		this.availableVirtualCores = availableVirtualCores;
	}

	public int getNumContainers() {
		return numContainers;
	}

	public void setNumContainers(int numContainers) {
		this.numContainers = numContainers;
	}
	
	public String toString() {
		return "nodeHostName: " + this.nodeHostName + "\n" + 
				"\t rack: " + this.rack + "\n" + 
				"\t state: " + this.state + "\n" + 
				"\t id: " + this.id + "\n" + 
				"\t nodeHTTPAddress: " + this.nodeHTTPAddress + "\n" + 
				"\t version: " + this.version + "\n" + 
				"\t healthReport: " + this.healthReport + "\n" + 
				"\t lastHealthUpdate: " + this.lastHealthUpdate + "\n" + 
				"\t usedMemoryMB: " + this.usedMemoryMB + "\n" + 
				"\t availMemoryMB: " + this.availMemoryMB + "\n" + 
				"\t usedVirtualCores: " + this.usedVirtualCores + "\n" + 
				"\t availableVirtualCores: " + this.availableVirtualCores + "\n" + 
				"\t numContainers: " + this.numContainers + "\n" ;
	}
}
