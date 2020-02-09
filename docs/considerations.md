## Considerations made during the project

### Assumptions
1. REST APIs being stateless, I have used simple redis cache to maintain session of the user


### Areas of extension
1. Redis can be replaced using service to maintain OAuth2 Security. 
   1. Token validations can be done via Oauth token / refresh token 
   2. Expiry time from Oauth can be passed to the user

