package cz.omeduna.rabbitmqspringamqp.tut1;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "cmsg.test.tutorial1.queue")
public class Consumer {

    @RabbitHandler()
    public void consume(String message) {
        System.out.println(" [x] Received '" + message + "'");
    }

}
