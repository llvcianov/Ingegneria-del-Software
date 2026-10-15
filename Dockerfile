FROM eclipse-temurin:17-jdk AS build
WORKDIR /app
COPY BiglietteriaCompagniaNavigazione/src ./src
RUN javac --release 17 -d bin $(find src -name "*.java")

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/bin ./bin
COPY BiglietteriaCompagniaNavigazione/mysql-connector-j-8.0.32.jar ./mysql-connector.jar
CMD ["java", "-cp", "bin:mysql-connector.jar", "boundary.BoundaryCliente"]
