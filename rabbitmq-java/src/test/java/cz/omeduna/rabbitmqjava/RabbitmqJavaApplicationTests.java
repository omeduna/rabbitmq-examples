package cz.omeduna.rabbitmqjava;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.GetResponse;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class RabbitmqJavaApplicationTests {

    private static final String QUEUE_NAME = "cmsg.test.queue";
    private static final String MESSAGE = "Hello Rabbit!!!";

    @Test
    public void produceMessagesTest() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            for (int i = 0; i < 3; i++) {
                channel.basicPublish("", QUEUE_NAME, null, MESSAGE.getBytes());
                System.out.println("\n [x] Sent '" + MESSAGE + "'");
            }
        }
    }

    @Test
    public void getMessagesTest() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            while (true) {

                GetResponse response = channel.basicGet(QUEUE_NAME, true);

                if (response != null) {
                    System.out.println("\nResponse: " + response);
                } else {
                    System.out.println("\nNO MORE MESSAGES!!!");
                    break;
                }
            }
        }
    }

    @Test
    public void consumeMessagesTest() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + message + "'");
            };

            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

            Thread.sleep(1000);
        }
    }

}

