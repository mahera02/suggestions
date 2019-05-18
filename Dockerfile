# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="radhika4cse@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/suggestions-0.0.1-SNAPSHOT.jar

# Add master data
ADD cities_canada-usa.tsv.txt cities_canada-usa.tsv.txt

# Add the application's jar to the container
ADD ${JAR_FILE} suggestions-0.0.1-SNAPSHOT.jar

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/suggestions-0.0.1-SNAPSHOT.jar"]
