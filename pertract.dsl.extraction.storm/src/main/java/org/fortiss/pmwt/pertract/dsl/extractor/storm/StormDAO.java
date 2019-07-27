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
package org.fortiss.pmwt.pertract.dsl.extractor.storm;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import org.glassfish.jersey.client.ClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StormDAO {

	private Client client;
	private WebTarget webtarget;
	private static final String API_PATH = "/api/v1";
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'GMT'";
	private final Logger log = LoggerFactory.getLogger(StormDAO.class);

	public StormDAO(String serverUrl) {
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
			webtarget.request().get(new GenericType<List<ClusterSummaryDTO>>() {
			});
		} catch (Exception e) {
			log.info("Exception", e);
			return false;
		}
		return true;
	}

	// url path: /api/v1/cluster/configuration
	public String getClusterConfiguration(){
		return webtarget
				.path("cluster")
				.path("configuration")
				.request().get(new GenericType<String>() {} );
	}
	
	// url path: /api/v1/cluster/summary
	public ClusterSummaryDTO getClusterSummary() {
		ClusterSummaryDTO clusterSummary = webtarget
				.path("cluster")
				.path("summary")
				.request().get(new GenericType<ClusterSummaryDTO>() {});
		return clusterSummary;
	}
	
	// url path: /api/v1/supervisor/summary 
	public SupervisorSummaryDTO getSupervisorSummary() {
		SupervisorSummaryDTO supervisorSummaryDTO = webtarget
				.path("supervisor")
				.path("summary")
				.request().get(new GenericType<SupervisorSummaryDTO>() {} );
		return supervisorSummaryDTO;
	}
	
	// url path: /api/v1/topology/summary
	public TopologySummaryDTO getTopologySummaryDTO(){
		TopologySummaryDTO topologySummaryDTO = webtarget
				.path("topology")
				.path("summary")
				.request().get(new GenericType<TopologySummaryDTO>() {} );
		return topologySummaryDTO;
	}
	
	// url path: /api/v1/topology/:id
	public TopologyDTO getTopology(String topologyId) {
		TopologyDTO topologyDTO = webtarget
				.path("topology")
				.path(topologyId)
				.request().get(new GenericType<TopologyDTO>() {} );
		return topologyDTO;
	}
	
	// url path: /api/v1/topology/:id/component/:component
	public SpoutComponentDTO getSpoutComponent(String topologyId, String componentId) {
		SpoutComponentDTO spoutComponentDTO = webtarget
				.path("topology")
				.path(topologyId)
				.path("component")
				.path(componentId)
				.request().get(new GenericType<SpoutComponentDTO>() {} );
		return spoutComponentDTO;
	}
	
	public BoltComponentDTO getBoltComponent(String topologyId, String componentId) {
		BoltComponentDTO boltComponentDTO = webtarget
				.path("topology")
				.path(topologyId)
				.path("component")
				.path(componentId)
				.request().get(new GenericType<BoltComponentDTO>() {} );
		return boltComponentDTO;
	}
	
}
