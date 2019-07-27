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
package org.fortiss.pmwt.pertract.dsl.extractor.yarn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import org.fortiss.pmwt.pertract.dsl.extractor.yarn.node.NodeDTO;
import org.fortiss.pmwt.pertract.dsl.extractor.yarn.node.NodesDTO;
import org.glassfish.jersey.client.ClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YarnClient {

	private Client client;
	private WebTarget webtarget;
	private static final String API_PATH = "/ws/v1/cluster/nodes";
	private final Logger log = LoggerFactory.getLogger(YarnClient.class);

	public YarnClient(String serverUrl) {
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
			webtarget.request().get(new GenericType<Map<String, NodesDTO>>() { });
		} catch (Exception e) {
			log.info("Exception", e);
			return false;
		}
		return true;
	}

	public List<NodeDTO> getNodes() {
		Map<String, NodesDTO> nodesMap = webtarget.request().get(new GenericType<Map<String, NodesDTO>>() {	});

		for (NodesDTO nodes : nodesMap.values()) {
			return nodes.getNode();
		}
		return new ArrayList<NodeDTO>();
	}

	public NodeDTO getNode(String nodeId) {
		Map<String, NodeDTO> nodeMap = webtarget.path(nodeId).request().get(new GenericType<Map<String, NodeDTO>>() { });

		for (NodeDTO node : nodeMap.values()) {
			return node;
		}
		return new NodeDTO();
	}
}
