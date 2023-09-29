package org.test.microservice.rabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.test.microservice.database.repository.MessageRepository;
import org.test.microservice.mapper.MessageMapper;
import org.test.microservice.rabbit.dto.MessageRabbitDto;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class RabbitConsumer {

    private final MessageMapper messageMapper;
    private final MessageRepository messageRepository;

    @RabbitListener(queues = "${rabbitmq.message.queue-name}")
    public void receiveMessage(List<MessageRabbitDto> dto) {
        log.info("RabbitListener dto: {}", dto);
        messageRepository.saveAll(messageMapper.mapRabbitDtoList(dto));
    }
}
