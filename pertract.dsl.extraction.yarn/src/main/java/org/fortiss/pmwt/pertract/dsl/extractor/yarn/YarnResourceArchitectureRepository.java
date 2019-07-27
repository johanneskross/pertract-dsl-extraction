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

import org.fortiss.pmwt.pertract.dsl.ResourceArchitectureRepository;
import org.fortiss.pmwt.pertract.dsl.extractor.yarn.node.NodeDTO;
import org.fortiss.pmwt.pertract.dsl.model.resources.ClusterSpecification;
import org.fortiss.pmwt.pertract.dsl.model.resources.MemoryProcessingUnit;
import org.fortiss.pmwt.pertract.dsl.model.resources.NetworkChannel;
import org.fortiss.pmwt.pertract.dsl.model.resources.ProcessingResourceUnit;
import org.fortiss.pmwt.pertract.dsl.model.resources.ResourceArchitecture;
import org.fortiss.pmwt.pertract.dsl.model.resources.ResourceDSLFactory;
import org.fortiss.pmwt.pertract.dsl.model.resources.ResourceNode;
import org.fortiss.pmwt.pertract.dsl.model.resources.ResourceRole;
import org.fortiss.pmwt.pertract.dsl.model.resources.SchedulingPolicy;

public class YarnResourceArchitectureRepository implements ResourceArchitectureRepository {
	
	private YarnClient yarnClient;
	private ResourceDSLFactory factory;
	
	public YarnResourceArchitectureRepository(String address) {
		this.yarnClient = new YarnClient(address);
		this.factory = ResourceDSLFactory.eINSTANCE;
	}
	
	public ResourceArchitecture find() {
		ResourceArchitecture resourceArchitecture = factory.createResourceArchitecture();
		resourceArchitecture.setName("ResourceArchitecture");
		resourceArchitecture.getResourceNodes().add(createMasterResourceNode());
		for (NodeDTO nodeDTO : yarnClient.getNodes()) {
			if (nodeDTO.getState().equals("RUNNING")) {
				resourceArchitecture.getResourceNodes().add(extractResourceNode(nodeDTO));
			}
		}
		resourceArchitecture.setNetworkChannel(createNetworkChannel());
		return resourceArchitecture;
	}
	
	private ResourceNode createMasterResourceNode() {
		ResourceNode resourceNode = factory.createResourceNode();
		resourceNode.setName("MASTER");
		ClusterSpecification spec = factory.createClusterSpecification();
		spec.setResourceRole(ResourceRole.MASTER.getName());
		spec.setSchedulingPolicy(SchedulingPolicy.ROUND_ROBIN.getName());
		ProcessingResourceUnit unit = factory.createProcessingResourceUnit();
		unit.setProcessingRate(1000);
		unit.setReplications(1);
		resourceNode.setProcessingResourceUnit(unit);
		resourceNode.setClusterSpecification(spec);
		return resourceNode;
	}
	
	private NetworkChannel createNetworkChannel() {
		NetworkChannel channel = factory.createNetworkChannel();
		channel.setBandwidth(134217728); // 1 Gbit/s
		channel.setLatency(0.01);
		return channel;
	}
	
	private ResourceNode extractResourceNode(NodeDTO nodeDTO) {
		ResourceNode resourceNode = factory.createResourceNode();
		resourceNode.setName(nodeDTO.getNodeHostName().replaceAll("\\.","").replaceAll("-",""));
		resourceNode.setClusterSpecification(createClusterSpecification(nodeDTO));
		resourceNode.setProcessingResourceUnit(createProcessingResourceUnit(nodeDTO));
		resourceNode.setMemoryProcessingUnit(createMemoryProcessingUnit(nodeDTO));
		return resourceNode;
	}

	private MemoryProcessingUnit createMemoryProcessingUnit(NodeDTO nodeDTO) {
		MemoryProcessingUnit unit = factory.createMemoryProcessingUnit();
		unit.setCapacity(nodeDTO.getAvailMemoryMB());
		return unit;
	}

	private ProcessingResourceUnit createProcessingResourceUnit(NodeDTO nodeDTO) {
		ProcessingResourceUnit unit = factory.createProcessingResourceUnit();
		unit.setProcessingRate(1000);
		unit.setReplications((int)nodeDTO.getAvailableVirtualCores());
		return unit;
	}

	private ClusterSpecification createClusterSpecification(NodeDTO nodeDTO) {
		ClusterSpecification spec = factory.createClusterSpecification();
		spec.setResourceRole(ResourceRole.WORKER.getName());
		return spec;
	}

}
