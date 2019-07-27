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
package org.fortiss.pmwt.pertract.dsl.extractor.flink.jobs;

public class InputDTO {

	private int num;
	private String id;
	private String ship_strategy;
	private String local_strategy;
	private String exchange;
	
	public InputDTO() {
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShip_strategy() {
		return ship_strategy;
	}

	public void setShip_strategy(String ship_strategy) {
		this.ship_strategy = ship_strategy;
	}

	public String getLocal_strategy() {
		return local_strategy;
	}

	public void setLocal_strategy(String local_strategy) {
		this.local_strategy = local_strategy;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

}
