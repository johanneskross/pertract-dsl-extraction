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

import java.util.ArrayList;
import java.util.List;

import org.fortiss.pmwt.pertract.dsl.ApplicationExecutionArchitectureRepository;
import org.fortiss.pmwt.pertract.dsl.extractor.spark.application.ApplicationDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.spark.application.SparkDAO;
import org.fortiss.pmwt.pertract.dsl.model.application.ApplicationConfiguration;
import org.fortiss.pmwt.pertract.dsl.model.application.ApplicationDSLFactory;
import org.fortiss.pmwt.pertract.dsl.model.application.ApplicationExecutionArchitecture;
import org.fortiss.pmwt.pertract.dsl.model.application.ExecutionNode;

public class SparkApplicationExecutionRepository implements ApplicationExecutionArchitectureRepository {
	
	private ApplicationDSLFactory factory;
	private SparkDAO sparkDAO;
	private ApplicationNodeExtraction applicationNodeExtraction;
	private ApplicationConfigurationExtraction applicationConfigurationExtraction;
	
	public SparkApplicationExecutionRepository(String serverURL) {
		this.factory = ApplicationDSLFactory.eINSTANCE;
		this.sparkDAO = new SparkDAO(serverURL);
		this.applicationNodeExtraction = new ApplicationNodeExtraction(sparkDAO);
		this.applicationConfigurationExtraction = new ApplicationConfigurationExtraction(sparkDAO);
	}
	
	@Override
	public ApplicationExecutionArchitecture find(String applicationID) {
		ApplicationDTO applicationDTO = sparkDAO.getApplication(applicationID);
		ApplicationExecutionArchitecture applicationModel = factory.createApplicationExecutionArchitecture();
		applicationModel.setName(applicationDTO.getId());
		applicationModel.setFramework("SPARK");
		ApplicationConfiguration config = applicationConfigurationExtraction.extractConfig(applicationDTO);
		applicationModel.setApplicationConfiguration(config);
		ExecutionNode applicationNode =  applicationNodeExtraction.extractNodeGraph(applicationDTO, config);
		applicationModel.setTopNode(applicationNode);
		return applicationModel;
	}

	@Override
	public List<ApplicationExecutionArchitecture> findAll() {
		List<ApplicationExecutionArchitecture> applicationModels = new ArrayList<>();
		for (ApplicationDTO applicationDTO : sparkDAO.getApplications()) {
			ApplicationExecutionArchitecture applicationModel = this.find(applicationDTO.getId());
			applicationModels.add(applicationModel);
		}
		return applicationModels;
	}

}
