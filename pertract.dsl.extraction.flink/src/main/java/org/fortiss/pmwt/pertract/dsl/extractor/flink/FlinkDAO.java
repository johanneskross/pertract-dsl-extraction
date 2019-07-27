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

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import org.fortiss.pmwt.pertract.dsl.extractor.flink.jobs.JobDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.flink.jobs.JobPlanDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.flink.jobs.vertices.VertexAccumulatorsDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.flink.jobs.vertices.VertexDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.flink.jobs.vertices.VertexSubtaskTimeDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.flink.jobs.vertices.VertexTaskManagerDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.flink.jobs.vertices.subtasks.VertexSubtaskAccumulatorsDTO;
import org.glassfish.jersey.client.ClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlinkDAO {

	private Client client;
	private WebTarget webtarget;
	private static final String API_PATH = "/";
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'GMT'";
	private final Logger log = LoggerFactory.getLogger(FlinkDAO.class);

	public FlinkDAO(String serverUrl) {
		createClient();
		createWebresource(serverUrl + API_PATH);
	}

	private void createClient() {
		ClientConfig clientConfig = new ClientConfig();
		client = ClientBuilder.newClient(clientConfig);
	}

	private void createWebresource(String serverUrl) {
		this.webtarget = client.target(serverUrl);
	}

	public Boolean testConnection() {
		try {
			webtarget.request().get(new GenericType<List<JobOverviewDTO>>() {});
		} catch (Exception e) {
			log.info("Exception", e);
			return false;
		}
		return true;
	}

	// url path: /joboverview
	public JobOverviewDTO getJobOverview() {
		JobOverviewDTO jobOverview = webtarget
				.path("joboverview")
				.request().get(new GenericType<JobOverviewDTO>() {});
		return jobOverview;
	}

	// url path: /jobs/<jobid>
	public JobDTO getJob(String jobId) {
		JobDTO jobDTO = webtarget.path("jobs")
				.path(jobId)
				.request().get(new GenericType<JobDTO>() {});
		return jobDTO;
	}

	// url path: /jobs/<jobid>/vertices/<vertexid>
	public VertexDTO getVertex(String jobId, String vertexId) {
		VertexDTO vertexDTO = webtarget
				.path("jobs")
				.path(jobId)
				.path("vertices")
				.path(vertexId)
				.request().get(new GenericType<VertexDTO>() {} );
		return vertexDTO;
	}
	
	// url path: /jobs/<jobid>/vertices/<vertexid>/subtasktimes
	public VertexSubtaskTimeDTO getVertexSubtasktime(String jobId, String vertexId) {
		VertexSubtaskTimeDTO vertexSubtaskTimeDTO = webtarget
				.path("jobs")
				.path(jobId)
				.path("vertices")
				.path(vertexId)
				.path("subtasktimes")
				.request().get(new GenericType<VertexSubtaskTimeDTO>() {} );
		return vertexSubtaskTimeDTO;
	}
	
	// url path: /jobs/<jobid>/vertices/<vertexid>/taskmanagers
	public VertexTaskManagerDTO getVertexTaskManagers(String jobId, String vertexId) {
		VertexTaskManagerDTO vertexTaskManagerDTO = webtarget
				.path("jobs")
				.path(jobId)
				.path("vertices")
				.path(vertexId)
				.path("taskmanagers")
				.request().get(new GenericType<VertexTaskManagerDTO>() {} );
		return vertexTaskManagerDTO;
	}
	
	// url paht: /jobs/<jobid>/vertices/<vertexid>/accumulators
	public VertexAccumulatorsDTO getVertexAccumulators(String jobId, String vertexId) {
		VertexAccumulatorsDTO vertexAccumulatorsDTO = webtarget
				.path("jobs")
				.path(jobId)
				.path("vertices")
				.path(vertexId)
				.path("accumulators")
				.request().get(new GenericType<VertexAccumulatorsDTO>() {} );
		return vertexAccumulatorsDTO;
	}
	
	// url path: /jobs/<jobid>/vertices/<vertexid>/subtasks/accumulators
	public VertexSubtaskAccumulatorsDTO getVertexSubtaskAccumulators(String jobId, String vertexId) {
		VertexSubtaskAccumulatorsDTO vertexSubtaskAccumulatorsDTO = webtarget
				.path("jobs")
				.path(jobId)
				.path("vertices")
				.path(vertexId)
				.path("subtasks")
				.path("accumulators")
				.request().get(new GenericType<VertexSubtaskAccumulatorsDTO>() {} );
		return vertexSubtaskAccumulatorsDTO;
	}
	
	// url path: /jobs/<jobid>/plan
	public JobPlanDTO getJobPlan(String jobId) {
		JobPlanDTO jobPlanDTO = webtarget
				.path("jobs")
				.path(jobId)
				.path("plan")
				.request().get(new GenericType<JobPlanDTO>() {});
		return jobPlanDTO;
	}
	
	public static void main(String[] args) {
		FlinkDAO flinkClient = new FlinkDAO("localhost:40958");
		
		String jobId = flinkClient.getJobOverview().getFinished().get(0).getJid();
		String vertexId = flinkClient.getJob(jobId).getVertices().get(0).getId();
		
		System.out.println(flinkClient.getVertexSubtasktime(jobId, vertexId));
	}
	
}
