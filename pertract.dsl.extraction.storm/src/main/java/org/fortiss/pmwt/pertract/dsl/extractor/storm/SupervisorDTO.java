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

public class SupervisorDTO {

	private String id;
	private String name;
	private String status;
	private String uptime;
	private int tasksTotal;
	private int workersTotal;
	private int executorsTotal;
	
	public SupervisorDTO() {
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

}
