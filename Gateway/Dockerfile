FROM openjdk:21
EXPOSE 8080
ARG JAR_NAME
COPY /jars/${JAR_NAME} /app.jar
CMD ["java","-jar","app.jar"]