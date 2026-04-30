FROM  container-registry.oracle.com/graalvm/native-image:21 AS build
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline
COPY src ./src
RUN ./mvnw native:compile -Pnative -DskipTests -Dnative-image.xmx=5g -Dnative-image.build-args="-Ob"

FROM debian:bookworm-slim
WORKDIR /app
RUN apt-get update && apt-get install -y zlib1g && rm -rf /var/lib/apt/lists/*
COPY --from=build /app/target/my-orderfactory .
EXPOSE 9091
RUN chmod +x ./my-orderfactory
CMD ["./my-orderfactory"]