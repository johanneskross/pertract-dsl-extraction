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
package org.fortiss.pmwt.pertract.dsl.extractor.spark;

import org.fortiss.pmwt.pertract.dsl.extractor.spark.application.ApplicationDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.spark.application.SparkDAO;
import org.fortiss.pmwt.pertract.dsl.extractor.spark.environment.EnvironmentDTO;
import org.fortiss.pmwt.pertract.dsl.model.application.ApplicationConfiguration;
import org.fortiss.pmwt.pertract.dsl.model.application.ApplicationDSLFactory;
import org.fortiss.pmwt.pertract.dsl.model.application.ApplicationType;
import org.fortiss.pmwt.pertract.dsl.model.application.BatchConfiguration;
import org.fortiss.pmwt.pertract.dsl.model.application.MiniBatchConfiguration;

public class ApplicationConfigurationExtraction {
	
	private ApplicationDSLFactory factory;
	private SparkDAO sparkDAO;
	
	public ApplicationConfigurationExtraction(SparkDAO sparkDAO) {
		this.factory = ApplicationDSLFactory.eINSTANCE;
		this.sparkDAO = sparkDAO;
	}

	public ApplicationConfiguration extractConfig(ApplicationDTO applicationDTO) {
		String type = sparkDAO.getApplicationType(applicationDTO.getId());
		ApplicationConfiguration config;
		if (type.equals(ApplicationType.MINI_BATCH.getName())) {
			config = createMiniBatchApplicationConfiguration();
		} else {
			config = createBatchApplicationConfiguration();
		}
		EnvironmentDTO environment = sparkDAO.getEnvironment(applicationDTO.getId());
		config.setDefaultParallelism(extractParallelism(environment));
		config.setExecutors(extractExecutors(environment));
		config.setTaskSlotsPerExecutor(extractTraskSlotsPerExecutor(environment));
		config.setMemoryPerExecutor(extractMemoryPerExecutor(environment));
		config.setMemoryPerTaskSlot(extractMemoryPerTaskSlot(environment));
		return config;
	}

	private ApplicationConfiguration createMiniBatchApplicationConfiguration() {
		MiniBatchConfiguration miniBatchConfiguration = factory.createMiniBatchConfiguration();
		miniBatchConfiguration.setMiniBatchInterval(5); // TODO
		return miniBatchConfiguration;
	}
	
	private ApplicationConfiguration createBatchApplicationConfiguration() {
		BatchConfiguration batchConfiguration = factory.createBatchConfiguration();
		return batchConfiguration;
	}
	
	private int extractParallelism(EnvironmentDTO environment) {
		return Integer.valueOf(environment.getSparkProperties().get("spark.default.parallelism"));
	}
	
	private int extractExecutors(EnvironmentDTO environment) {
		return  Integer.valueOf(environment.getSparkProperties().get("spark.executor.instances"));
	}
	
	private int extractTraskSlotsPerExecutor(EnvironmentDTO environment) {
		return  Integer.valueOf(environment.getSparkProperties().get("spark.executor.cores"));
	}
	
	private int extractMemoryPerExecutor(EnvironmentDTO environment) {
		String executorMemory = environment.getSparkProperties().get("spark.executor.memory"); 
		int executorMemoryInGigabyte = Integer.valueOf(executorMemory.replaceAll("g", ""));
		int executorMemoryInMegabyte = 1024 * executorMemoryInGigabyte;
		return executorMemoryInMegabyte;
	}
	
	private int extractMemoryPerTaskSlot(EnvironmentDTO environment) {
		return extractMemoryPerExecutor(environment) / extractTraskSlotsPerExecutor(environment);
	}

	
}
