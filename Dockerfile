FROM openjdk:21-jdk

COPY target/ForceGym-Backend.jar .

EXPOSE 7000

ENTRYPOINT [ "java",  "-jar", "ForceGym-Backend.jar"]