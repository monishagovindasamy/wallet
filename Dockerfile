### Build with gradle binary caching ###
FROM eclipse-temurin:17-jdk as builder
RUN apt-get update && apt-get install -y dos2unix
WORKDIR source
COPY settings.gradle gradlew.bat gradlew build.gradle ./
COPY gradle ./gradle
RUN dos2unix gradlew
RUN chmod +x gradlew
COPY . .
RUN dos2unix gradlew
RUN chmod +x gradlew
RUN ./gradlew build

### Run ###
FROM eclipse-temurin:17-jdk as run
WORKDIR run
COPY --from=builder ./source/build/libs/wallet-0.0.1-SNAPSHOT.jar wallet.jar
EXPOSE 8080
CMD ["java","-jar","wallet.jar"]
