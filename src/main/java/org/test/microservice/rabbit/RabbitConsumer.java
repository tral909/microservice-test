package org.test.microservice.rabbit;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.test.microservice.database.repository.MessageRepository;
import org.test.microservice.mapper.MessageMapper;
import org.test.microservice.rabbit.dto.MessageRabbitDto;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
@Validated
public class RabbitConsumer {

    private final MessageMapper messageMapper;
    private final MessageRepository messageRepository;

    @RabbitListener(queues = "${rabbitmq.message.queue-name}")
    public void receiveMessage(@Valid @NotEmpty @Payload List<MessageRabbitDto> dto) {
        log.info("RabbitListener dto: {}", dto);
        messageRepository.saveAll(messageMapper.mapRabbitDtoList(dto));
    }
}
