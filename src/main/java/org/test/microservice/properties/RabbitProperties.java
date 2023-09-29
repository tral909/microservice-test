package org.test.microservice.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rabbitmq.message")
@Data
public class RabbitProperties {

    private String queueName;
    private String exchange;
    private String routingKey;
}