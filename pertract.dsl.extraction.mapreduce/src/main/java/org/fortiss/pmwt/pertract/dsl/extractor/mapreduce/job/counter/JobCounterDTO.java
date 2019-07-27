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
package org.fortiss.pmwt.pertract.dsl.extractor.mapreduce.job.counter;

public class JobCounterDTO {

	private String name;
	private long reduceCounterValue;
	private long mapCounterValue;
	private long totalCounterValue;
	
	public JobCounterDTO(){
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getReduceCounterValue() {
		return reduceCounterValue;
	}

	public void setReduceCounterValue(long reduceCounterValue) {
		this.reduceCounterValue = reduceCounterValue;
	}

	public long getMapCounterValue() {
		return mapCounterValue;
	}

	public void setMapCounterValue(long mapCounterValue) {
		this.mapCounterValue = mapCounterValue;
	}

	public long getTotalCounterValue() {
		return totalCounterValue;
	}

	public void setTotalCounterValue(long totalCounterValue) {
		this.totalCounterValue = totalCounterValue;
	}
	
}
