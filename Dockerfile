FROM maven:3.5.3-jdk-8 as mvn
COPY src /usr/src/peakwork/src
COPY pom.xml /usr/src/peakwork
RUN mvn -f /usr/src/peakwork/pom.xml clean package
RUN mv /usr/src/peakwork/target/*.war /usr/src/peakwork/target/peakwork.war

FROM tomcat:9.0.10-jre8
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=mvn /usr/src/peakwork/target/peakwork.war /usr/local/tomcat/webapps
