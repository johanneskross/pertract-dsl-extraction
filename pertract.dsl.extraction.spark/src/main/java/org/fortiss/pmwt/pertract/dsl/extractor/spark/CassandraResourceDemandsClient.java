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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;

public class CassandraResourceDemandsClient {

	final Logger log = LoggerFactory.getLogger(CassandraResourceDemandsClient.class);
	private Cluster cluster;
	private Session session;
	public final static String SPLIT_STRING = "-";
	private String host = "localhost";
	private String keyspace = "bd_monitoring";
	private String table = "cpu_samples_thread_info";
	
	public long getCPUTime(String experiment, String stackTraceSubString, Date startTime, Date endTime) {
		this.cluster = Cluster.builder().addContactPoint(host).build();
		this.session = this.cluster.connect(keyspace);
		long aggregatedCPUTime = something(experiment, stackTraceSubString, startTime, endTime);
		log.debug("CPU time: " + aggregatedCPUTime);
		this.session.close();
		this.cluster.close();
		return aggregatedCPUTime;
	}
	
	
	private long something(String experiment, String stackTraceSubString, Date startTime, Date endTime) {
		String experimentName = experiment;
		String increasedExperimentName = increaseMinuteOfExperimentName(experimentName);
		long aggregatedCPUTime = 0L;
		boolean overallMatch = false;
		ResultSet rangedTraces = this.queryJvmsAndThreadsForExperiment(experimentName);
		for (Row distinctTrace : rangedTraces.all()) {
			String experiment_name = distinctTrace.getString("experiment_name");
			boolean localMatch = experiment_name.equals(experimentName) || experiment_name.equals(increasedExperimentName);
			if (localMatch) {
				overallMatch = true;
				String jvm = distinctTrace.getString("jvm");
				String stacktrace = distinctTrace.getString("thread_group_and_name");
				if (stacktrace.contains(stackTraceSubString)) {
					long cpuTimeInTimeRange = getCPUTimeInTimeRange(stackTraceSubString, experiment_name, jvm, stacktrace, startTime, endTime);
					long cpuTimeBeforeStartTime = getCPUTimeBeforeStartTime(stackTraceSubString, experiment_name, jvm, stacktrace, startTime, endTime);
					long cpuTimeIncrease = cpuTimeInTimeRange - cpuTimeBeforeStartTime;
					aggregatedCPUTime += cpuTimeIncrease >= 0 ? cpuTimeIncrease : cpuTimeInTimeRange;
				}
			}
		}
		if (!overallMatch) throw new RuntimeException("No records available for experiment");
		return aggregatedCPUTime;
	}
	
	private String increaseMinuteOfExperimentName(String experimentName) {
		String updatedExperimentName = experimentName;
		try {
			SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd-HH:mm");
			Date experimentDate = fmt.parse(experimentName);
			updatedExperimentName = fmt.format(new Date(experimentDate.getTime()+60000));
		} catch (ParseException e) {
			log.warn("Could not format experiment name to date");
		}
		return updatedExperimentName;
	}


	private long getCPUTimeInTimeRange(String stackTraceSubString, String experiment_name, String jvm, String stacktrace, Date startTime, Date endTime) {
//		long cpuTime = 0L;
		ResultSet traces = queryInTimeRange(experiment_name,jvm,stacktrace,startTime,endTime);
		// TODO Use SSTable Attached Secondary Index in Cassandra to query substrings
		// we substract the first measured cpu time from the last measured cpu time
		long latestCPUTime = 0L;
		long firstCPUTime = 0L;
		int index = 0;
		for (Row trace : traces) {
			if (trace.getString("thread_group_and_name")!=null && trace.getString("thread_group_and_name").contains(stackTraceSubString)) {
				long cpuTime = trace.getLong("cpu_time");
				if (index == 0) latestCPUTime = cpuTime;
				firstCPUTime = cpuTime;
//				cpuTime += trace.getLong("cpu_time");
			}
			index++;
		}
		long cpuTime = 0;
		if (latestCPUTime > firstCPUTime) {
			cpuTime = latestCPUTime - firstCPUTime;
		}  else {
			cpuTime = latestCPUTime;
		}
		log.debug(stacktrace + ": " + cpuTime);
		return cpuTime;
	}
	
	private long getCPUTimeBeforeStartTime(String stackTraceSubString, String experiment_name, String jvm, String stacktrace, Date startTime, Date endTime) {
		long cpuTime = 0L;
		ResultSet traces = this.queryBeforeTime(experiment_name, jvm, stacktrace, startTime, endTime);
		// TODO Use SSTable Attached Secondary Index in Cassandra to query substrings
		for (Row trace : traces) {
			if (!trace.isNull(0) && trace.getString("thread_group_and_name").contains(stackTraceSubString)) {
				cpuTime += trace.getLong("cpu_time");
			}
		}
		return cpuTime;
	}
	
	private ResultSet queryInTimeRange(Date startTime, Date endTime) {
		Statement statement = QueryBuilder
				.select("experiment_name", "jvm", "thread_group_and_name")
				.from(table)
				.allowFiltering()
				.where(QueryBuilder.gte("sample_time", startTime.getTime()))
				.and(QueryBuilder.lte("sample_time", endTime.getTime()));
		log.debug("CQL: " + statement.toString());
		return session.execute(statement);
	}
	
	private ResultSet queryJvmsAndThreadsForExperiment(String experiment) {
		Statement statement = QueryBuilder
				.select("experiment_name", "jvm", "thread_group_and_name").distinct()
				.from(table);
//				.allowFiltering()
//				.where(QueryBuilder.eq("experiment_name", experiment));
		log.debug("CQL: " + statement.toString());
		return session.execute(statement);
	}
	
	private ResultSet queryInTimeRange(String experiment_name, String jvm, String stacktrace, Date startTime, Date endTime) {
		Statement statement = QueryBuilder
				.select().all()
				.from(table)
				.where(QueryBuilder.eq("experiment_name", experiment_name))
				.and(QueryBuilder.eq("jvm", jvm))
				.and(QueryBuilder.eq("thread_group_and_name", stacktrace))
				.and(QueryBuilder.gte("sample_time", startTime.getTime()))
				.and(QueryBuilder.lte("sample_time", endTime.getTime()))
				.orderBy(QueryBuilder.desc("sample_time"));
//				.limit(1);
		log.debug("CQL: " + statement.toString());
		return session.execute(statement);
	}
	
	private ResultSet queryBeforeTime(String experiment_name, String jvm, String stacktrace, Date startTime, Date endTime) {
		Statement statement =QueryBuilder
				.select().column("thread_group_and_name").column("cpu_time")
				.from(table)
				.where(QueryBuilder.eq("experiment_name", experiment_name))
				.and(QueryBuilder.eq("jvm", jvm))
				.and(QueryBuilder.eq("thread_group_and_name", stacktrace))
				.and(QueryBuilder.lt("sample_time", startTime.getTime()))
				.orderBy(QueryBuilder.desc("sample_time"))
				.limit(1);
		log.debug("CQL: " + statement.toString());
		return this.session.execute(statement);
	}
	
}
