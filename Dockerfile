FROM openjdk:11-jdk
EXPOSE 8081
ADD target/DevOps_Project-2.1.jar DevOps_Project-2.1.jar
ENTRYPOINT ["java","-jar","/DevOps_Project-2.1.jar"]