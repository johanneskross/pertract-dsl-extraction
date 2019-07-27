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
package org.fortiss.pmwt.pertract.dsl.extractor.hdfs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HdfsClient {

	private final Logger log = LoggerFactory.getLogger(HdfsClient.class);
	private Configuration conf;

	public HdfsClient(String serverUrl) {
		this.conf = new Configuration();
		this.conf.set("fs.defaultFS", serverUrl);
		this.conf.set("io.compression.codec.bzip2.library", "java-builtin");
	}

	public List<LocatedFileStatus> readFiles(String folderPath) {
		List<LocatedFileStatus> fileStatuses = new ArrayList<>();
		try {
			FileSystem fs = FileSystem.get(conf);
			Path path = new Path(folderPath);
			RemoteIterator<LocatedFileStatus> fileIterator = fs.listFiles(path, true);
			while(fileIterator.hasNext()) {
				LocatedFileStatus fileStatus = fileIterator.next();
				if (fileStatus.getLen()>0) {
					fileStatuses.add(fileStatus);
				}
			}
			fs.close();
		} catch (IOException e) {
			log.error("Path " + folderPath + " does not exist", e);
		}
		return fileStatuses;
	}

}
