FROM openjdk:17-oracle AS jre

FROM jre AS runner

WORKDIR /app

COPY /target/producer-1.jar /app/application.jar

ENV JAVA_TOOL_OPTIONS -Dfile.encoding=UTF8

ENTRYPOINT java -jar /app/application.jar
