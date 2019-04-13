package cz.omeduna.rabbitmqspringamqp.tut1;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("tut1")
@Configuration
public class Tut1Config {

    @Bean
    public Queue queue() {
        return new Queue("cmsg.test.tutorial1.queue");
    }

    @Bean
    public Consumer consumer() {
        return new Consumer();
    }

    @Bean
    public Producer producer() {
        return new Producer();
    }

}
