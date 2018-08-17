web: java $JAVA_OPTS -Dserver.port=$PORT -Dspring.profiles.active=producer,heroku -jar target/*.jar
worker1: java $JAVA_OPTS -Dspring.profiles.active=consumer,heroku -jar target/*.jar
worker2: java $JAVA_OPTS -Dspring.profiles.active=consumer,heroku -jar target/*.jar
