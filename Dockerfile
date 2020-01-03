FROM java:8-jdk-alpine
COPY ./target/call-for-blood-service-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch call-for-blood-service-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","call-for-blood-service-0.0.1-SNAPSHOT.jar"]