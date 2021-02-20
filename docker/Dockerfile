# This Dockerfile is used in order to build a container that runs the Quarkus application in JVM mode
#
# Before building the docker image run:
#
# mvn package
#
# Then, build the image with:
#
# docker build -f docker/Dockerfile -t melloware/quarkus-faces .
#
# Then run the container using:
#
# docker run -i --rm -p 8080:8080 melloware/quarkus-faces
#
FROM fabric8/java-alpine-openjdk11-jre
COPY target/lib/* /deployments/lib/
COPY target/*-runner.jar /deployments/app.jar
RUN export JAVA_OPTIONS="$JAVA_OPTIONS -Xmx128m -Xms128m"
ENTRYPOINT [ "/deployments/run-java.sh" ]