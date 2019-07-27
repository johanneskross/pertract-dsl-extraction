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

import java.util.ArrayList;
import java.util.List;

import org.fortiss.pmwt.pertract.dsl.ApplicationExecutionArchitectureRepository;
import org.fortiss.pmwt.pertract.dsl.extractor.flink.jobs.CompactVertexDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.flink.jobs.JobDTO;
import org.fortiss.pmwt.pertract.dsl.model.application.ApplicationDSLFactory;
import org.fortiss.pmwt.pertract.dsl.model.application.ApplicationExecutionArchitecture;

public class FlinkApplicationExecutionRepository implements ApplicationExecutionArchitectureRepository {

	private FlinkDAO flinkDao;
	private ApplicationDSLFactory factory;
	
	public FlinkApplicationExecutionRepository(String serverURL) {
		this.flinkDao = new FlinkDAO(serverURL);
		this.factory = ApplicationDSLFactory.eINSTANCE;
	}
	
	@Override
	public ApplicationExecutionArchitecture find(String applicationId) {
		JobDTO job = flinkDao.getJob(applicationId);
		ApplicationExecutionArchitecture appModel = factory.createApplicationExecutionArchitecture();
		appModel.setName(job.getName());
		appModel.setFramework("FLINK");
		List<CompactVertexDTO> vertices = job.getVertices();
		//TODO
		for (CompactVertexDTO vertex : vertices) {
			vertex.getName();
		}
		
		return appModel;
	}

	@Override
	public List<ApplicationExecutionArchitecture> findAll() {
		List<ApplicationExecutionArchitecture> applications = new ArrayList<>();
		JobOverviewDTO jobOverviewDTO = flinkDao.getJobOverview();
		for (CompactJobDTO job : jobOverviewDTO.getFinished()) {
			String id = job.getJid();
			ApplicationExecutionArchitecture application = this.find(id);
			applications.add(application);
		}
		for (CompactJobDTO job : jobOverviewDTO.getRunning()) {
			String id = job.getJid();
			ApplicationExecutionArchitecture application = this.find(id);
			applications.add(application);
		}
		return applications;
	}

}
