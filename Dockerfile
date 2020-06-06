FROM maven:3.6.3-openjdk-11 AS MAVEN_TOOL_CHAIN
COPY pom.xml /maven-build/
COPY src/ /maven-build/src/
WORKDIR /maven-build/
RUN mvn clean package

FROM openjdk:11.0.7-jdk
WORKDIR /root/
COPY --from=0 /maven-build/target/simulator-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "simulator-0.0.1-SNAPSHOT.jar"]