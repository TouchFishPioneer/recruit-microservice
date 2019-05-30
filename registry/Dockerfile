FROM openjdk:8u212-jre-slim
ADD build/libs/*.jar eureka_8761.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/eureka_8761.jar"]