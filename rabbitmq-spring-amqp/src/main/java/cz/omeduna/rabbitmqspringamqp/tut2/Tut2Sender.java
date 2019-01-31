package cz.omeduna.rabbitmqspringamqp.tut2;

import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.atomic.AtomicInteger;

public class Tut2Sender {

    AtomicInteger dots = new AtomicInteger(0);
    AtomicInteger count = new AtomicInteger(0);

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        StringBuilder builder = new StringBuilder("Hello");
        if (dots.incrementAndGet() == 3) {
            dots.set(1);
        }
        for (int i = 0; i < dots.get(); i++) {
            builder.append('.');
        }
        builder.append(count.incrementAndGet());
        String message = builder.toString();

        MessagePostProcessor postProcessor = message1 -> {
            MessageProperties properties = message1.getMessageProperties();
            properties.setMessageId("My message ID - 123456789");
            return message1;
        };
        try {
            template.convertAndSend(queue.getName(), message.getBytes("UTF-8"), postProcessor);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println(" [x] Sent '" + message + "'");
    }

}
