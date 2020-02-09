## Considerations made during the project

### Assumptions
1. REST APIs being stateless, I have used simple redis cache to maintain session of the user
2. Postgres is the backend DB used to hold user info. 

### Areas of extension
1. Redis can be replaced using service to maintain OAuth2 Security. 
   1. Token validations can be done via Oauth token / refresh token 
   2. Expiry time from Oauth can be passed to the user
2. Idempotency is implemented for Login for now. We can extend it for otehr APIs if needed
3. Response POJOs are added into separate classes for success and error so that it can be extended separately for Error and success responses. Specific msgs can be included for clarity
