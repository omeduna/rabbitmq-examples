package cz.omeduna.rabbitmqspringamqp.tut1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Tut1SpringBootApplication {

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(Tut1SpringBootApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner() {
        return (args) -> {
            System.out.println("Starting...");
            Thread.sleep(5*1000);
            System.out.println("Shutting down");
            applicationContext.close();
        };
    }

}

