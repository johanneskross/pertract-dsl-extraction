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

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.fs.LocatedFileStatus;
import org.fortiss.pmwt.pertract.dsl.DataWorkloadArchitectureRepository;
import org.fortiss.pmwt.pertract.dsl.model.dataworkload.DataWorkloadArchitecture;
import org.fortiss.pmwt.pertract.dsl.model.dataworkload.DataWorkloadDSLFactory;
import org.fortiss.pmwt.pertract.dsl.model.dataworkload.FileDataModel;
import org.fortiss.pmwt.pertract.dsl.model.dataworkload.FileSpecification;
import org.fortiss.pmwt.pertract.dsl.model.dataworkload.SingleDataSource;

public class HdfsDataWorkloadRepository implements DataWorkloadArchitectureRepository {

	private HdfsClient client;
	private DataWorkloadDSLFactory factory;
	private final int bytes_to_megabytes = 1024*1024;
	
	public HdfsDataWorkloadRepository(String serverURL) {
		this.factory = DataWorkloadDSLFactory.eINSTANCE;
		this.client = new HdfsClient(serverURL);
	}
	
	@Override
	public DataWorkloadArchitecture find(String folderPath) {
		FileDataModel fileDataModel = createFileDataModel();
		List<LocatedFileStatus> fileStatuses = client.readFiles(folderPath);
		List<Long>blockSizes = new ArrayList<>();
		for (LocatedFileStatus fileStatus : fileStatuses) {
			blockSizes.add(fileStatus.getBlockSize());
			fileDataModel.getFiles().add(createFileSpecification(fileStatus.getLen()));
		}
		double meanBlockSizeInBytes = blockSizes.stream().mapToLong(val -> val).average().getAsDouble();
		fileDataModel.setPartitionSize((int) meanBlockSizeInBytes / bytes_to_megabytes);
		fileDataModel.setDataSource(createClosedDataSource());
		DataWorkloadArchitecture dataWorkload = createDataWorkloadArchitecture(folderPath.replace("/", ""), fileDataModel);
		return dataWorkload;
	}
	
	private DataWorkloadArchitecture createDataWorkloadArchitecture(String name, FileDataModel dataModel) {
		DataWorkloadArchitecture dataWorkload = factory.createDataWorkloadArchitecture();
		dataWorkload.setName(name);
		dataWorkload.getDataModels().add(dataModel);
		return dataWorkload;
	}
	
	private FileDataModel createFileDataModel() {
		return factory.createFileDataModel();
	}
	
	private FileSpecification createFileSpecification(long sizeInBytes) {
		FileSpecification fileSpecification = factory.createFileSpecification();
		fileSpecification.setSize(sizeInBytes / bytes_to_megabytes );
		return fileSpecification;
	}
	
	private SingleDataSource createClosedDataSource() {
		return factory.createSingleDataSource();
	}

}
