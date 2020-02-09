# Sample Request and Response

## Register API

#### Request
```
curl --location --request POST 'http://localhost:8080/users/register' \
--header 'Content-Type: application/json' \
--data-raw '{
	"userName":"swws",
	"password" :"sample",
	"email" : "abc@xyz.com"
}'
```
#### Response

```
{
    "message": "SUCCESS"
}
```

## Login

#### Request
```
curl --location --request POST 'http://localhost:8080/users/login' \
--header 'Content-Type: application/json' \
--data-raw '{
	"userName":"swws",
	"password" :"sample"
}'
```
#### Response
```
{
    "accessToken": "c0a138b1-32ce-4106-be76-e4062006e093"
}
```
## Execute task

#### Request
```
curl --location --request POST 'http://localhost:8080/tasks/execute' \
--header 'access-token: c0a138b1-32ce-4106-be76-e4062006e093' \
--header 'Content-Type: application/json' \
--data-raw '{
	"operationType" :"ADD",
	"operands":[
		"298673","9876251"
		]
}'
```
#### Response
```
{
    "output": "10174924"
}
```
## View history

#### Request
```
curl --location --request GET 'http://localhost:8080/tasks/history' \
--header 'access-token: c0a138b1-32ce-4106-be76-e4062006e093' \
--header 'Content-Type: application/json' \
--data-raw ''
```
#### Response
```
{
    "logs": [
        {
            "response": {
                "output": "1286324"
            },
            "request": {
                "operationType": "ADD",
                "operands": [
                    "298673",
                    "987651"
                ]
            },
            "time": "2020-02-09T09:16:38.218+0000"
        },
        {
            "response": {
                "output": "10174924"
            },
            "request": {
                "operationType": "ADD",
                "operands": [
                    "298673",
                    "9876251"
                ]
            },
            "time": "2020-02-09T09:16:49.765+0000"
        },
        {
            "response": {
                "output": "10174924"
            },
            "request": {
                "operationType": "ADD",
                "operands": [
                    "298673",
                    "9876251"
                ]
            },
            "time": "2020-02-09T09:17:04.765+0000"
        }
    ]
}
```
## Logout

#### Request
```
curl --location --request POST 'http://localhost:8080/users/logout' \
--header 'access-token: f557ece3-1403-4f82-b1bd-7a4c8ddd26d2'
```
#### Response
```
{
    "message": "LOGGED_OUT"
}
```