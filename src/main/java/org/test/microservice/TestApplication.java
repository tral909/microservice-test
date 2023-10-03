package org.test.microservice;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.test.microservice.properties.RabbitProperties;
import org.test.microservice.rabbit.dto.MessageRabbitDto;

import java.util.List;

@EnableCaching
@EnableScheduling
@ConfigurationPropertiesScan("org.test.microservice.properties")
@SpringBootApplication
public class TestApplication implements CommandLineRunner {

    @Autowired
    RabbitTemplate template;
    @Autowired
    RabbitProperties rabbitProperties;

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var dto = new MessageRabbitDto();
        dto.setId(1L);
        dto.setTo("test1");
        dto.setText("HELLO VASYAN1");
        dto.setType(10);
        template.convertAndSend(rabbitProperties.getExchange(), rabbitProperties.getRoutingKey(), List.of(dto));

        var dto2 = new MessageRabbitDto();
        dto2.setId(2L);
        dto2.setTo("test2");
        dto2.setText("HELLO VASYAN2");
        dto2.setType(20);

        var dto3 = new MessageRabbitDto();
        dto3.setId(3L);
        dto3.setTo("test3");
        dto3.setText("HELLO VASYAN3");
        dto3.setType(30);
        template.convertAndSend(rabbitProperties.getExchange(), rabbitProperties.getRoutingKey(), List.of(dto2, dto3));
    }

}
