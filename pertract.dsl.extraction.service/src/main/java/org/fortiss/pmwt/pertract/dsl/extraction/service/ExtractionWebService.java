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

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.fortiss.pmwt.pertract.dsl.ApplicationExecutionArchitectureRepository;
import org.fortiss.pmwt.pertract.dsl.extraction.service.configuration.ExtractionConfigDTO;
import org.fortiss.pmwt.pertract.dsl.extraction.service.repository.PerTractDSL;
import org.fortiss.pmwt.pertract.dsl.extraction.service.repository.PerTractDSLRepository;
import org.fortiss.pmwt.pertract.dsl.extractor.ApplicationExecutionArchitectureRepositoryFactory;
import org.fortiss.pmwt.pertract.dsl.extractor.ApplicationExecutionArchitectureRepositoryType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.annotations.Api;

@Singleton
@Path("/extraction")
@Api(value = "/extraction")
public class ExtractionWebService {
	
	private static final Logger log = LoggerFactory.getLogger(ExtractionWebService.class);
	private SortedMap<Integer,List<ExtractionConfigDTO>> queue = Collections.synchronizedSortedMap(new TreeMap<Integer,List<ExtractionConfigDTO>>());
	private Map<Integer, String> inProgress = Collections.synchronizedMap(new HashMap<Integer, String>());
	private Map<Integer, Integer> results = Collections.synchronizedMap(new HashMap<Integer, Integer>());
	private Thread extractionService;
	private int id = 0;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postExtraction(List<ExtractionConfigDTO> extractionConfigs) {
		this.startExtractionService();
		try {
			if ( (id + 1) == Integer.MAX_VALUE) {
				id = 0;
			}
			id++;
			log.info("New extraction config with id "+ id);
			queue.put(id, extractionConfigs);
			return Response.status(Status.ACCEPTED).location(URI.create("/extraction/"+id)).build();
		} catch (Exception e) {
			log.error("Error during extraction", e);
			return Response.serverError().entity(e).build();
		}
	}
	
	

	@Path("/{id}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatus(@PathParam("id") int id) {
		log.info("Status requested for extraction config with id " + id);
		synchronized (queue) {
			if (queue.containsKey(id)) {
				return Response.ok().header("status", "Waiting in queue").build();
			}
		}
		synchronized (inProgress) {
			if (inProgress.containsKey(id)) {
				return Response.ok().header("status", inProgress.get(id)).build();
			}
		}
		synchronized (results) {
			if (results.containsKey(id)) {
				return Response.seeOther(URI.create("/extraction/" + id + "/results")).build();
			}
		}
		return Response.status(Status.NO_CONTENT).build();
	}
	
	@Path("/{id}/results")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResult(@PathParam("id") int id) {
		log.info("Results requested for extraction config with id " + id);
		if (results.containsKey(id)) {
			int result = results.get(id);
			return Response.ok(result).header("status", "Results are available")
					.location(URI.create("/extraction/"+id+"/results")).build();
		} else {
			return  Response.status(Status.NO_CONTENT).build();
		}
	}
	
	@Path("/{id}/results")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteResult(@PathParam("id") int id) {
		log.info("Removing extraction config with id " + id);
		if (results.containsKey(id)) {
			results.remove(id);
			return Response.ok().build();
		} else {
			return  Response.status(Status.NO_CONTENT).build();
		}
	}
	
	private void startExtractionService() {
		if (this.extractionService == null) {
			log.info("Starting extraction service");
			this.extractionService = new Thread(new ExtractionService(queue, inProgress, results));
			this.extractionService.start();
		}
	}
	
}
