package cz.omeduna.rabbitmqspringamqp.tut3;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class Producer {

    private static final String MESSAGE = "Hello Rabbit from Spring AMQP";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private FanoutExchange fanoutExchange;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void produce() {

        rabbitTemplate.convertAndSend(fanoutExchange.getName(), "", MESSAGE);

        System.out.println(" [x] Sent '" + MESSAGE + "'");
    }

}
