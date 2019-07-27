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
package org.fortiss.pmwt.pertract.dsl.extraction.service;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.fortiss.pmwt.pertract.dsl.ApplicationExecutionArchitectureRepository;
import org.fortiss.pmwt.pertract.dsl.DataWorkloadArchitectureRepository;
import org.fortiss.pmwt.pertract.dsl.ResourceArchitectureRepository;
import org.fortiss.pmwt.pertract.dsl.extraction.service.configuration.ExtractionConfigDTO;
import org.fortiss.pmwt.pertract.dsl.extraction.service.repository.MongoPerTractDSLRepository;
import org.fortiss.pmwt.pertract.dsl.extraction.service.repository.PerTractDSL;
import org.fortiss.pmwt.pertract.dsl.extraction.service.repository.PerTractDSLRepository;
import org.fortiss.pmwt.pertract.dsl.extractor.ApplicationExecutionArchitectureRepositoryFactory;
import org.fortiss.pmwt.pertract.dsl.extractor.ApplicationExecutionArchitectureRepositoryType;
import org.fortiss.pmwt.pertract.dsl.extractor.DataWorkloadArchitectureRepositoryFactory;
import org.fortiss.pmwt.pertract.dsl.extractor.DataWorkloadArchitectureRepositoryType;
import org.fortiss.pmwt.pertract.dsl.extractor.ResourceArchitectureRepositoryFactory;
import org.fortiss.pmwt.pertract.dsl.extractor.ResourceArchitectureRepositoryType;
import org.fortiss.pmwt.pertract.dsl.model.application.ApplicationExecutionArchitecture;
import org.fortiss.pmwt.pertract.dsl.model.dataworkload.DataWorkloadArchitecture;
import org.fortiss.pmwt.pertract.dsl.model.resources.ResourceArchitecture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtractionService implements Runnable {

	private static final Logger log = LoggerFactory.getLogger(ExtractionService.class);
	private SortedMap<Integer, List<ExtractionConfigDTO>> queue;
	private Map<Integer, String> inProgress;
	private Map<Integer, Integer> results;
	private PerTractDSLRepository repository;
	
	public ExtractionService(SortedMap<Integer, List<ExtractionConfigDTO>> queue, Map<Integer, String> inProgress, Map<Integer, Integer> results) {
		this.queue = queue;
		this.inProgress = inProgress;
		this.results = results;
		this.repository = new MongoPerTractDSLRepository();
	}
	
	@Override
	public void run() {
		boolean run = true;
		try {
			while(run) {
				if (queue.isEmpty()) {
					Thread.sleep(1000);
				} else {
					int id = queue.firstKey();
					List<ExtractionConfigDTO> configs = queue.get(id);
					inProgress.put(id, "In progress");
					queue.remove(id);
					this.extract(id, configs);
				}
			}
		} catch (InterruptedException e) {
            log.error("Could not run simulation service", e);
        }	
	}
	
	private void extract(int id, List<ExtractionConfigDTO> extractionConfigs) {
		PerTractDSL dsl = new PerTractDSL();
		
		for (ExtractionConfigDTO config : extractionConfigs) {
			
			ApplicationExecutionArchitectureRepositoryType appType = getAppFrameworkType(config);
			if (!appType.equals(ApplicationExecutionArchitectureRepositoryType.NONE)) {
				dsl.setApplicationExecutionArchitecture(extractExecutionArchitecture(id, config, appType));
			}
			
			DataWorkloadArchitectureRepositoryType dataType = getDataFrameworkType(config);
			if (!dataType.equals(DataWorkloadArchitectureRepositoryType.NONE)) {
				dsl.setDataWorkloadArchitecture(extractDataWorkloadArchitecture(id, config, dataType));
			}
			
			ResourceArchitectureRepositoryType resourceType = getResourceFrameworkType(config);
			if (!resourceType.equals(ResourceArchitectureRepositoryType.NONE)) {
				dsl.setResourceArchitecture(extractResourceArchitecture(id, config, resourceType));
			}
			
		}
		
		int hashCode = System.identityHashCode(dsl);
		dsl.set_id(hashCode);
		repository.store(dsl);
		results.put(id, hashCode);
		inProgress.remove(id);	
	}
	
	
	private ResourceArchitecture extractResourceArchitecture(int id, ExtractionConfigDTO config, ResourceArchitectureRepositoryType resourceType) {
		String status = "Extracting resource architecture";
		log.info(status);
		inProgress.put(id, status);
		
		ResourceArchitectureRepositoryFactory resourceFactory = new ResourceArchitectureRepositoryFactory();
		ResourceArchitectureRepository resourceRepo = resourceFactory.getResourceArchitectureRepository(resourceType, config.getUri());
		return resourceRepo.find();
	}

	private DataWorkloadArchitecture extractDataWorkloadArchitecture(int id, ExtractionConfigDTO config, DataWorkloadArchitectureRepositoryType dataType) {
		String status = "Extracting data workload architecture";
		log.info(status);
		inProgress.put(id, status);
		
		DataWorkloadArchitectureRepositoryFactory dataFactory = new DataWorkloadArchitectureRepositoryFactory();
		DataWorkloadArchitectureRepository dataRepo = dataFactory.getDataWorkloadArchitectureRepository(dataType, config.getUri());
		return dataRepo.find(config.getIdentifier());
	}

	private ApplicationExecutionArchitecture extractExecutionArchitecture(int id, ExtractionConfigDTO config, ApplicationExecutionArchitectureRepositoryType appType) {
		String status = "Extracting execution architecture";
		log.info(status);
		inProgress.put(id, status);
		
		ApplicationExecutionArchitectureRepositoryFactory appFactory = new ApplicationExecutionArchitectureRepositoryFactory();
		ApplicationExecutionArchitectureRepository appRepo = appFactory.getApplicationExecutionArchitectureRepository(appType, config.getUri());
		return appRepo.find(config.getIdentifier());
	}
	
	private ApplicationExecutionArchitectureRepositoryType getAppFrameworkType(ExtractionConfigDTO config) {
		ApplicationExecutionArchitectureRepositoryType[] constants = ApplicationExecutionArchitectureRepositoryType.class.getEnumConstants();
		for ( ApplicationExecutionArchitectureRepositoryType type : constants) {
			if (config.getFramework().name().toLowerCase().equals(type.name().toLowerCase())) {
				return type;
			}
		}
		return ApplicationExecutionArchitectureRepositoryType.NONE;
	}
	
	private DataWorkloadArchitectureRepositoryType getDataFrameworkType(ExtractionConfigDTO config) {
		DataWorkloadArchitectureRepositoryType[] constants = DataWorkloadArchitectureRepositoryType.class.getEnumConstants();
		for ( DataWorkloadArchitectureRepositoryType type : constants) {
			if (config.getFramework().name().toLowerCase().equals(type.name().toLowerCase())) {
				return type;
			}
		}
		return DataWorkloadArchitectureRepositoryType.NONE;
	}
	
	private ResourceArchitectureRepositoryType getResourceFrameworkType(ExtractionConfigDTO config) {
		ResourceArchitectureRepositoryType[] constants = ResourceArchitectureRepositoryType.class.getEnumConstants();
		for ( ResourceArchitectureRepositoryType type : constants) {
			if (config.getFramework().name().toLowerCase().equals(type.name().toLowerCase())) {
				return type;
			}
		}
		return ResourceArchitectureRepositoryType.NONE;
	}
	
}
