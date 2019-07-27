# PerTract DSL Extraction

* License: Eclipse Public License - v 2.0

 ## Requirements / Dependencies
_Requirements: Java Oracle JDK or OpenJDK, Apache Maven_

You need to have installed the pertract.dsl roject into your workspace or local maven repository

## Build from Source
Either import the project in your IDE or install it in your local Maven repo using

```bash
mvn -f pertract.dsl.extraction.flink/pom.xml clean install
mvn -f pertract.dsl.extraction.hdfs/pom.xml clean install
mvn -f pertract.dsl.extraction.mapreduce/pom.xml clean install
mvn -f pertract.dsl.extraction.spark/pom.xml clean install
mvn -f pertract.dsl.extraction.storm/pom.xml clean install
mvn -f pertract.dsl.extraction.yarn/pom.xml clean install
mvn -f pertract.dsl.extraction/pom.xml clean install
```

## Use Extraction
* for applications (i.e., Apache Spark)
```java
String appId = ""; // ID of your spark application

ApplicationExecutionArchitectureRepositoryFactory appFactory = 
    new ApplicationExecutionArchitectureRepositoryFactory();

ApplicationExecutionArchitectureRepository appRepo = 
    appFactory.getApplicationExecutionArchitectureRepository(
        ApplicationExecutionArchitectureRepositoryType.SPARK, "http://<IP>:18081");

ApplicationExecutionArchitecture applicationExecutionArchitecture = appRepo.find(appId);
```

* for data (i.e., Apache HDFS)
```java
String folderPath = ""; // Path on your HDFS 

DataWorkloadArchitectureRepositoryFactory dataFactory = 
    new DataWorkloadArchitectureRepositoryFactory();

DataWorkloadArchitectureRepository dataRepo = 
    dataFactory.getDataWorkloadArchitectureRepository(
        DataWorkloadArchitectureRepositoryType.HDFS, "http://<IP>:8020");

DataWorkloadArchitecture dataWorkloadArchitecture = dataRepo.find(folderPath);
```

* for resources (i.e., Apache YARN)
```java
ResourceArchitectureRepositoryFactory resourceFactory = 
    new ResourceArchitectureRepositoryFactory();

ResourceArchitectureRepository resourceRepo = 
    resourceFactory.getResourceArchitectureRepository(
        ResourceArchitectureRepositoryType.YARN, "http://<IP>:8088");

ResourceArchitecture resourceArchitecture = resourceRepo.find();
```