package opticaltelephonecompany.otc.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import opticaltelephonecompany.otc.consumer.RabbitMQConsumer;
import opticaltelephonecompany.otc.dto.RegistrationDto;
import opticaltelephonecompany.otc.event.InvoiceGenerationEvent;
import opticaltelephonecompany.otc.models.Call;
import opticaltelephonecompany.otc.models.CallReceiver;
import opticaltelephonecompany.otc.models.Users;

@Service
public class RabbitMQJsonProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);
   
    private RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(Users user) {
        LOGGER.info(String.format("Json message sent -> %s", user.toString()));
        rabbitTemplate.convertAndSend(exchange, routingJsonKey, user);
    }

    public void sendJsonMessage(RegistrationDto body) {
        LOGGER.info(String.format("Json message sent -> %s", body.toString()));
        rabbitTemplate.convertAndSend(exchange, routingJsonKey, body);
    }

    public void sendJsonMessage(CallReceiver body) {
        LOGGER.info(String.format("Json message sent -> %s", body.toString()));
        rabbitTemplate.convertAndSend(exchange, routingJsonKey, body);
    }

    public void sendJsonMessage(Call body) {
        LOGGER.info(String.format("Json message sent -> %s", body.toString()));
        rabbitTemplate.convertAndSend(exchange, routingJsonKey, body);
        // rabbitTemplate.convertAndSend(exchange, routingJsonKey, body);
    }

      // Method to send InvoiceGenerationEvent as JSON message
    public void sendJsonMessage(InvoiceGenerationEvent event) {
        LOGGER.info(String.format("Json message sent -> %s", event.toString()));
        rabbitTemplate.convertAndSend(exchange, routingJsonKey, event);
    }


}
