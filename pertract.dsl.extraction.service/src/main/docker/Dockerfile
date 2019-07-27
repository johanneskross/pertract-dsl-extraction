FROM openjdk:jre-alpine

ADD pertract.dsl.extraction.service-1.0.0-swarm.jar /opt/wildfly-swarm.jar

EXPOSE 8080
ENTRYPOINT exec java -Dmongo_host=$MONGO_HOST -Dmongo_port=$MONGO_PORT -Xmx1024m -jar /opt/wildfly-swarm.jar
