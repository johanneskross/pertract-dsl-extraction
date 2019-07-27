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
package org.fortiss.pmwt.pertract.dsl.extractor;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.fortiss.pmwt.pertract.dsl.ApplicationExecutionArchitectureRepository;
import org.fortiss.pmwt.pertract.dsl.extractor.flink.FlinkApplicationExecutionRepository;
import org.fortiss.pmwt.pertract.dsl.extractor.mapreduce.MapReduceApplicationExecutionRepository;
import org.fortiss.pmwt.pertract.dsl.extractor.spark.SparkApplicationExecutionRepository;
import org.fortiss.pmwt.pertract.dsl.extractor.storm.StormApplicationExecutionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


// aka context of strategy pattern
public class ApplicationExecutionArchitectureRepositoryFactory {

	// type equals policy, strategy equals ApplicationRepository
	private Map<ApplicationExecutionArchitectureRepositoryType, Class<? extends ApplicationExecutionArchitectureRepository>> registeredRepositories;
	private final Logger log = LoggerFactory.getLogger(ApplicationExecutionArchitectureRepositoryFactory.class);
	
	public ApplicationExecutionArchitectureRepositoryFactory() {
		this.registeredRepositories = new HashMap<>();
		this.registeredRepositories.put(ApplicationExecutionArchitectureRepositoryType.SPARK, SparkApplicationExecutionRepository.class);
		this.registeredRepositories.put(ApplicationExecutionArchitectureRepositoryType.FLINK, FlinkApplicationExecutionRepository.class);
		this.registeredRepositories.put(ApplicationExecutionArchitectureRepositoryType.STORM, StormApplicationExecutionRepository.class);
		this.registeredRepositories.put(ApplicationExecutionArchitectureRepositoryType.MAPREDUCE, MapReduceApplicationExecutionRepository.class);
	}

	public void registerRepository(ApplicationExecutionArchitectureRepositoryType type, ApplicationExecutionArchitectureRepository repository) {
		this.registeredRepositories.put(type, repository.getClass());
	}
	
	public ApplicationExecutionArchitectureRepository getApplicationExecutionArchitectureRepository (ApplicationExecutionArchitectureRepositoryType type, String address) {
		if (registeredRepositories.containsKey(type)) {
			try {
				Class<? extends ApplicationExecutionArchitectureRepository> clazz = registeredRepositories.get(type);
				Constructor<? extends ApplicationExecutionArchitectureRepository> ctor;
				ctor = clazz.getConstructor(String.class);
				return ctor.newInstance(new Object[] { address });
			} catch (Exception e) {
				log.error("Could not create repository", e);
			}
			return null;
		} else {
			throw new IllegalArgumentException("Unsupported repository type! Registered types: " + registeredRepositories.keySet());
		}
	}
	
}
