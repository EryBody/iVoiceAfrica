FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/ivoiceafrica-app.jar
ADD ${JAR_FILE} ivoiceafrica-app.jar
ENTRYPOINT ["java","-jar","/ivoiceafrica-app.jar"]