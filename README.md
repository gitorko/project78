# Project 78

Spring & RabbitMQ

[https://gitorko.github.io/spring-amqp/](https://gitorko.github.io/spring-amqp/)

### Version

Check version

```bash
$java --version
openjdk 17.0.3 2022-04-19 LTS
```

### RabbitMQ

Run the docker command to start a rabbitmq instance

```bash
docker run -d --hostname my-rabbit --name my-rabbit -e RABBITMQ_DEFAULT_USER=guest -e RABBITMQ_DEFAULT_PASS=guest -p 8080:15672 -p 5672:5672 rabbitmq:3-management
```

Open the rabbitmq console

[http://localhost:8080](http://localhost:8080)

```
user:guest
pwd: guest
```

### Dev

```bash
./gradlew bootRun
```
