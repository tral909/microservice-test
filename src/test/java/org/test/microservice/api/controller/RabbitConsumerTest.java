package org.test.microservice.api.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.test.library.MetricService;
import org.test.microservice.database.entity.MessageEntity;
import org.test.microservice.database.repository.MessageRepository;
import org.test.microservice.rabbit.RabbitConsumer;
import org.test.microservice.rabbit.dto.MessageRabbitDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RabbitConsumerTest extends AbstractTest {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private RabbitConsumer rabbitConsumer;
    @MockBean
    private MetricService metricService;

    @Test
    public void testReceiveMessage() {
        var dto = new MessageRabbitDto();
        dto.setId(100L);
        dto.setFrom("rabbit");
        dto.setTo("consumer");
        dto.setText("hello world");
        dto.setType(20);
        var dtos = List.of(dto);
        rabbitConsumer.receiveMessage(dtos);

        Optional<MessageEntity> actualMsg = messageRepository.findById(100L);
        Assertions.assertTrue(actualMsg.isPresent());
        MessageEntity actual = actualMsg.get();
        Assertions.assertEquals(dto.getId(), actual.getId());
        Assertions.assertEquals(dto.getFrom(), actual.getFrom());
        Assertions.assertEquals(dto.getTo(), actual.getTo());
        Assertions.assertEquals(dto.getText(), actual.getText());
        Assertions.assertEquals(dto.getType(), actual.getType().getId());

        Mockito.verify(metricService).save(Map.of("EMAIL", 1L));
    }
}
