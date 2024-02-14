package opticaltelephonecompany.otc.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import opticaltelephonecompany.otc.models.RegistrationDto;


@Service
public class RabbitMQJsonConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);
       

    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void consumeJsonMessage(RegistrationDto body) {
        LOGGER.info(String.format("Received JSON message -> %s", body.toString()));
    }
}
