FROM maven:3.9.6-eclipse-temurin-21
COPY . ./source_code
RUN mvn -B package -Dmaven.test.skip --file ./source_code/pom.xml
RUN mv ./source_code/auth/target/auth-0.0.1.jar /client-0.0.1.jar
EXPOSE 8081
CMD ["java", "-jar","/client-0.0.1.jar"]