package cz.omeduna.rabbitmqjava;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
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
    public void consumeMessagesTest() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {

//            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

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

}

