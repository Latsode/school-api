# Running Application

1. Clone the repository
2. Gradle must be installed

### Using Docker
1. open terminal in the root directory of the project
2. run docker-compose up --build
3. open browser and go to http://localhost:9090/swagger-ui/index.html#/
4. use the swagger ui to test the endpoints
5. run docker-compose down to stop the containers

### Using Gradle in IntelliJ
1. open terminal in the root directory of the project
2. run gradle bootRun from side menu
3. open browser and go to http://localhost:9090/swagger-ui/index.html#/

### Using IDE
1. open the project in your IDE
2. run the main method in the SchoolApiApplication class
3. open browser and go to http://localhost:9090/swagger-ui/index.html#/

### Using Gradle in terminal
1. open terminal in the root directory of the project
2. ensure you have java 17 using java -version
3. run gradlew bootRun from terminal
4. open browser and go to http://localhost:9090/swagger-ui/index.html#/