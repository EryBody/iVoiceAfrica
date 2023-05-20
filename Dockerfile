FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
EXPOSE 9092
EXPOSE 9093
ARG JAR_FILE=target/ivoiceafrica-app.jar
ADD ${JAR_FILE} ivoiceafrica-app.jar
ENTRYPOINT ["java","-jar","/ivoiceafrica-app.jar"]