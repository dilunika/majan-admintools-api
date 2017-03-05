FROM airdock/oracle-jdk

COPY build/libs/majan-admintools-api-1.0-SNAPSHOT-fat.jar /app/

CMD ["java", "-jar", "/app/majan-admintools-api-1.0-SNAPSHOT-fat.jar"]