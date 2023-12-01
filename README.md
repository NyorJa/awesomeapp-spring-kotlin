# awesomeapp-spring-kotlin

## Requirements
1. JDK 17
2. IDE of your choice (Intellij)
3. Gradle 7.6.1
4. Docker

## How to start
1. Go to the root directory of the project
2. Change branch to sql `git checkout sql`
3. `docker compose up -d` to enable mysql and phpmyadmin and got to browser check `localhost:8085/` username and password is `admin`
4. `./gradlew  clean build` 
5. `./gradlew bootRun`  
