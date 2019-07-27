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

import org.fortiss.pmwt.pertract.dsl.DataWorkloadArchitectureRepository;
import org.fortiss.pmwt.pertract.dsl.extractor.hdfs.HdfsDataWorkloadRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


// aka context of strategy pattern
public class DataWorkloadArchitectureRepositoryFactory {

	// type equals policy, strategy equals DataWorkloadRepository
	private Map<DataWorkloadArchitectureRepositoryType, Class<? extends DataWorkloadArchitectureRepository>> registeredRepositories;
	private final Logger log = LoggerFactory.getLogger(DataWorkloadArchitectureRepositoryFactory.class);
	
	public DataWorkloadArchitectureRepositoryFactory() {
		this.registeredRepositories = new HashMap<>();
		this.registeredRepositories.put(DataWorkloadArchitectureRepositoryType.HDFS, HdfsDataWorkloadRepository.class);
	}

	public void registerRepository(DataWorkloadArchitectureRepositoryType type, DataWorkloadArchitectureRepository repository) {
		this.registeredRepositories.put(type, repository.getClass());
	}
	
	public DataWorkloadArchitectureRepository getDataWorkloadArchitectureRepository (DataWorkloadArchitectureRepositoryType type, String address) {
		if (registeredRepositories.containsKey(type)) {
			try {
				Class<? extends DataWorkloadArchitectureRepository> clazz = registeredRepositories.get(type);
				Constructor<? extends DataWorkloadArchitectureRepository> ctor;
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
