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
package org.fortiss.pmwt.pertract.dsl.extraction.service.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoPerTractDSLRepository implements PerTractDSLRepository {
	
	private static final Logger log = LoggerFactory.getLogger(MongoPerTractDSLRepository.class);
	private MongoCollection<PerTractDSL> collection;
	
	public MongoPerTractDSLRepository() {
		collection = createMongoCollection();
	}
	
	private MongoCollection<PerTractDSL> createMongoCollection() {
		String connection = "mongodb://";
		String mongoHost = System.getProperty("mongo_host");
		String mongoPort = System.getProperty("mongo_port");
		if (mongoHost != null && mongoPort != null) {
			connection += mongoHost + ":" + mongoPort;
		} else {
			connection += "localhost";
		}
		log.info("Connecting to mongo at " + connection);
		
		MongoClientSettings settings = MongoClientSettings.builder()
				.applyConnectionString(new ConnectionString(connection)).build();
		MongoClient mongoClient = MongoClients.create(settings);
		MongoDatabase database = mongoClient.getDatabase("pertract");
		return database.getCollection("dsl", PerTractDSL.class);
	}

	public void store(PerTractDSL dsl) {
		collection.insertOne(dsl);
	}
	
}
