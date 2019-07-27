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

import java.util.List;

public class NodeDTO {

	private String id;
	private int parallelism;
	private String operator;
	private String operator_strategy;
	private String description;
	private List<InputDTO> inputs;
	private OptimizerPropertiesDTO optimizer_properties;
	
	public NodeDTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getParallelism() {
		return parallelism;
	}

	public void setParallelism(int parallelism) {
		this.parallelism = parallelism;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperator_strategy() {
		return operator_strategy;
	}

	public void setOperator_strategy(String operator_strategy) {
		this.operator_strategy = operator_strategy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<InputDTO> getInputs() {
		return inputs;
	}

	public void setInputs(List<InputDTO> inputs) {
		this.inputs = inputs;
	}

	public OptimizerPropertiesDTO getOptimizer_properties() {
		return optimizer_properties;
	}

	public void setOptimizer_properties(OptimizerPropertiesDTO optimizer_properties) {
		this.optimizer_properties = optimizer_properties;
	}
}
