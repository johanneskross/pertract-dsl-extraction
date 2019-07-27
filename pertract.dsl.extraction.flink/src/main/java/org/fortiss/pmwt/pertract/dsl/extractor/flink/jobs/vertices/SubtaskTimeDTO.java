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

import org.fortiss.pmwt.pertract.dsl.extractor.flink.jobs.TimestampsDTO;

public class SubtaskTimeDTO {

	private int subtask;
	private String host;
	private long duration;
	private TimestampsDTO timestamps;
	
	public SubtaskTimeDTO() {
	}

	public int getSubtask() {
		return subtask;
	}

	public void setSubtask(int subtask) {
		this.subtask = subtask;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public TimestampsDTO getTimestamps() {
		return timestamps;
	}

	public void setTimestamps(TimestampsDTO timestamps) {
		this.timestamps = timestamps;
	}
		
}
