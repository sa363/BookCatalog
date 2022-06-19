FROM openjdk:11 as builder

WORKDIR application
COPY target/BookCatalog-0.0.1-SNAPSHOT.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM openjdk:11
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/application/ ./


CMD java -Xmx512m org.springframework.boot.loader.JarLauncher --server.port=8080

EXPOSE 8080