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
package org.fortiss.pmwt.pertract.dsl.extractor.flink;

import java.util.List;

public class JobOverviewDTO {

	private List<CompactJobDTO> running;
	private List<CompactJobDTO> finished;
	
	public JobOverviewDTO() {
	}

	public List<CompactJobDTO> getRunning() {
		return running;
	}

	public void setRunning(List<CompactJobDTO> running) {
		this.running = running;
	}

	public List<CompactJobDTO> getFinished() {
		return finished;
	}

	public void setFinished(List<CompactJobDTO> finished) {
		this.finished = finished;
	}
	
}
