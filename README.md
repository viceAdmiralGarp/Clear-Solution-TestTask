## cURLs for testing

### Create user

``` shell 
curl --location 'http://localhost:8080/users' \
--header 'Content-Type: application/json' \
--data-raw '{
"email": "testex@ample.com",
"firstName": "Test",
"lastName": "User",
"dateOfBirth": "2003-04-30",
"address": "123 Test Street",
"phoneNumber": "1234567890"
}'
```

### Partially update user

```shell
curl --location --request PATCH 'http://localhost:8080/users/a8c9d2b1-d3e3-47d2-a5df-d3f30f4b4615' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "test123@example.com",
    "firstName": "Test123",
    "lastName": "User123",
    "dateOfBirth": "2005-04-23",
    "address": "dasf Test Street",
    "phoneNumber": "ads"
}'
```

### Fully update user

```shell
curl --location --request PUT 'http://localhost:8080/users/fb54a4bf-4ca8-4877-9ce7-289c82dfaafb' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "up@example.com",
    "firstName": "up",
    "lastName": "up",
    "dateOfBirth": "2004-04-23",
    "address": "up",
    "phoneNumber": "up"
}'
```

### Delete user

```shell
curl --location --request DELETE 'http://localhost:8080/users/fb54a4bf-4ca8-4877-9ce7-289c82dfaafb'
```

### Get specific range of birthdays

```shell
curl --location 'http://localhost:8080/users?from=2001-04-23&to=2005-04-23'
```