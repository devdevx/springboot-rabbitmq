mvnw spring-boot:run -Dspring-boot.run.profiles=producer,dev
mvnw spring-boot:run -Dspring-boot.run.profiles=producer,heroku
mvnw spring-boot:run -Dspring-boot.run.profiles=consumer,dev
mvnw spring-boot:run -Dspring-boot.run.profiles=consumer,heroku
docker-compose up
docker-compose down

http://localhost:15672/


Ready to run in heroku with the CloudAMQP add-on.

TODO end readme

- End readme

basic -> Default exchange
broadcast -> Fanout exchange
direct -> Direct exchange
Topic exchange
Headers exchange