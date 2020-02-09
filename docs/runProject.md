## Instructions to run and deploy

Application is set up using Docker. Docker image link is provided in the Readme

### Steps to run the app via uploaded image:
 1. Comment the build option under the services->application in docker-compose.yml
 2. Uncomment the image option  under the services->application in docker-compose.yml
 3. docker-compose up 

### Steps to run the app locally:
 1. docker-compose build (update any changes made locally)
 2. docker-compose up 
 
### Steps to hit API :

 1. Import the postman collection via the link provided in Readme
 2. Use the register API to create a new user 
 3. With the userName and password created, use the login API to get the `accessToken`
 4. Going forward we would use this token in the Header with param `access-token`
 5. Execute API can be called with Json body.
    1. It expects `operationType` parameter to have `ADD`. Other operations are not yet implemented. Open to more operations 
    2. `operands` is an array of Strings to be passed for executing the task
 6. History API can be called with the header