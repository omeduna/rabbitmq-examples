package cz.omeduna.rabbitmqspringamqp.tut3;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class Consumer {

    @RabbitListener(queues = "#{autoDeleteQueue1.name}")
    public void consume1(String message) {
        System.out.println(" [1] Received '" + message + "'");
    }

    @RabbitListener(queues = "#{autoDeleteQueue2.name}")
    public void consume2(String message) {
        System.out.println(" [2] Received '" + message + "'");
    }

}
