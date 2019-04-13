package cz.omeduna.rabbitmqspringintegration;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:rabbitmq-inbound.xml")
public class RabbitmqSpringIntegrationConfig {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            for (int i = 1; i <= 100; i++) {
                System.out.println("Sending message number " + i);
                rabbitTemplate.convertAndSend("bar.exchange", "foo.bar.xxx", "Hello".getBytes());
                Thread.sleep(100);
            }
        };
    }

}
