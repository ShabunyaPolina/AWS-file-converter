# file-processing-cadence-app
Cadence Spring Boot application

### How to use
Before launching the application enter your AWS account credentials
into `AWSAccount.properties` file.  
Run `docker-compose up` to start cadence server.  
Then run `run.bat` or `run.sh` to start application.  
(`docker run --network=host --rm ubercadence/cli:master --do Domain domain register -rd 1`
registers a domain.)  
(`mvn spring-boot:run` starts Spring Boot application.)

- Workflow can be started using REST:
  GET http://localhost:8080/{fileName}  
  **fileName** must be a valid file name with .csv extension.
- Cadence GUI can be accessed through http://localhost:8088
