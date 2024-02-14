package opticaltelephonecompany.otc.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


import opticaltelephonecompany.otc.models.Call;
import opticaltelephonecompany.otc.models.CallReceiverDto;
import opticaltelephonecompany.otc.models.RegistrationDto;
import opticaltelephonecompany.otc.models.Users;


@Service
public class RabbitMQJsonConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);
       

    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void consumeJsonMessage(RegistrationDto body) {
        LOGGER.info(String.format("Received JSON message -> %s", body.toString()));
    }

    @RabbitListener(queues = { "${rabbitmq.queue.json.name}" })
    public void consumeJsonMessage(CallReceiverDto body) {
        LOGGER.info(String.format("Received JSON message -> %s", body.toString()));
    }

    @RabbitListener(queues = { "${rabbitmq.queue.json.name}" })
    public void consumeJsonMessage(Call body) {
        LOGGER.info(String.format("Received JSON message -> %s", body.toString()));
    }

    @RabbitListener(queues = { "${rabbitmq.queue.json.name}" })
    public void consumeJsonMessage(Users body) {
        LOGGER.info(String.format("Received JSON message -> %s", body.toString()));
    }
}
