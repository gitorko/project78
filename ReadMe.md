# project78

[https://gitorko.github.io/](https://gitorko.github.io/)

RabbitMQ Spring

## Setup

### RabbitMQ

Run the docker command to start a rabbitmq instance

```bash
docker run -d --hostname my-rabbit -p 8080:15672 -p 5672:5672 rabbitmq:3-management
```

You can login to the rabbitmq console with username:guest password: guest

[http://localhost:8080](http://localhost:8080)

{% asset_img image01.png %}
