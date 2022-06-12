# Wishlist REST API example
- 
- [x] Spring Boot
- [x] Spring Data
- [x] MongoDB
- [x] Lombok
- [x] Docker
- [x] Unit Tests

**System requirements**
- JDK 11
- Maven
- Docker

## Build and run application
The file [docker-compose.yml](docker-compose.yml) contains all the necessary settings to configure the environemnt. As this is a very simple application, we will basically have only two containers/services - the rest api and the database. If you want to  build or rebuild services to include any code changes in the docker image(s), run `docker-compose build`. To start all the services, you need to run `docker-compose up` (use `-d` to run in detached mode). The docker image for the rest api application service is defined by the file [Dockerfile](Dockerfile) using multi stages in order to build and execute the application.

If you prefer not to use docker, you can manually build and generate the executable file with `mvn clean package` and then run the maven spring boot plugin (`mvn spring-boot:run`) or directly the java command `java -jar <jar file location>`. Do not forget to set the required environment variables for the [application.properties](/src/main/resources/application.properties). You must be running an instance of MongoDB server.

The rest api application will be available at http://localhost:8080/v1/wishlist.

