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

import org.fortiss.pmwt.pertract.dsl.ResourceArchitectureRepository;
import org.fortiss.pmwt.pertract.dsl.extractor.yarn.YarnResourceArchitectureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//aka context of strategy pattern
public class ResourceArchitectureRepositoryFactory {
	
	private final Logger log = LoggerFactory.getLogger(ResourceArchitectureRepositoryFactory.class);
	
	// type equals policy, strategy equals DataWorkloadRepository
	private Map<ResourceArchitectureRepositoryType, Class<? extends ResourceArchitectureRepository>> registeredRepositories;
	
	public ResourceArchitectureRepositoryFactory() {
		this.registeredRepositories = new HashMap<>();
		this.registeredRepositories.put(ResourceArchitectureRepositoryType.YARN, YarnResourceArchitectureRepository.class);
	}
	
	public void registerRepository(ResourceArchitectureRepositoryType type, ResourceArchitectureRepository repository) {
		this.registeredRepositories.put(type, repository.getClass());
	}
	
	public ResourceArchitectureRepository getResourceArchitectureRepository (ResourceArchitectureRepositoryType type, String address) {
		if (registeredRepositories.containsKey(type)) {
			try {
				Class<? extends ResourceArchitectureRepository> clazz = registeredRepositories.get(type);
				Constructor<? extends ResourceArchitectureRepository> ctor;
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
