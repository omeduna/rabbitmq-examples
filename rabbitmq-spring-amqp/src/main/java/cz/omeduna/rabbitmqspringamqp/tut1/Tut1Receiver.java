package cz.omeduna.rabbitmqspringamqp.tut1;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "queueTut1")
public class Tut1Receiver {

    @RabbitHandler
    public void receive(String message) {
        System.out.println(" [x] Received '" + message + "'");
    }

}
