FROM java:openjdk-8
RUN mkdir -p /opt/person-service
ADD target/person-service-1.0-SNAPSHOT.jar /opt/person-service
WORKDIR /opt/person-service
CMD ["java", "-jar", "person-service-1.0-SNAPSHOT.jar"]
EXPOSE 8080