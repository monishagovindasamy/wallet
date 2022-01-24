# Wallet Micro Service

## Dependencies
- Latest Intellij IDE version
- Java 17+ SDK
- H2 database
- Liquibase  
- gradle
- Docker


## Technology stack
- Type: web server application
- Language: Java 17
- Framework: Spring Boot framework
- Build tool: Gradle

## Start the application

### Using Intellij:
- Open the project in Intellij and press the green arrow in toolbar
- if the green arrow is disabled then:
    - select **WalletApplication** configuration from the dropdown on the left of it

## Build the application
- build runnable jar using the below command
    ```shell script
    ./gradlew bootJar
    ```

Distributable .jar will be located in /build/target folder

## Swagger UI
- after starting the application via intellij or docker, use below url to access Swagger UI
    - [Swagger UI](http://localhost:8080/api/swagger-ui/)
    - This will list all Api's

## Test the API via Swagger

###To create Player
- /api/player (POST) 
    - the below JSON example for creating Player
    ```json
   {
      "account": {
      "balance": 25.5
      },
      "address": "Stockholm",
      "name": "Player1"
      } 
  ```

###To get balance by player
- /api/player/{playerId}/balance (GET)
    - playerId - Unique auto generated number
  
###To get all Transactions by Player
- /api/transaction/{playerId} (GET)
  - playerId - Unique auto generated number
  
###To Create Credit Transaction
- /api/transaction/credit (POST)
    - the below JSON example for creating credit Transaction
   ```json
    {
      "amount": 50,
      "description": "savings",
      "playerId": 1,
      "type": "CREDIT"
    }
  ```
  
  ###To Create Debit Transaction
- /api/transaction/debit (POST)
  - the below JSON example for creating debit Transaction
   ```json
    {
      "amount": 25,
      "description": "For Some game purchase",
      "playerId": 1,
      "type": "DEBIT"
    }
  ```

## Test the application
- run test by running test task in intellij
- or by running the next command
    ```shell script
    # run this in backend folder
    ./gradlew test
    ```

## Docker image
- build docker image
    ```shell
    # run this in backend folder
    docker build --tag=wallet .
    ```
- run docker compose
    ```shell
    # run this in backend folder
    docker-compose up
    ```