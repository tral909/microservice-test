package org.test.microservice.rabbit;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.test.library.MetricService;
import org.test.microservice.en.MessageType;
import org.test.microservice.mapper.MessageMapper;
import org.test.microservice.rabbit.dto.MessageRabbitDto;
import org.test.microservice.usecase.GetMessageUseCase;
import org.test.microservice.usecase.SaveMessageUseCase;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Validated
public class RabbitConsumer {

    private final MetricService metricService;
    private final MessageMapper messageMapper;
    private final GetMessageUseCase getMessageUseCase;
    private final SaveMessageUseCase saveMessageUseCase;

    @RabbitListener(queues = "${rabbitmq.message.queue-name}")
    public void receiveMessage(@Valid @NotEmpty @Payload List<MessageRabbitDto> dto) {
        log.info("RabbitListener dto: {}", dto);
        saveMessageUseCase.saveAll(messageMapper.mapRabbitDtoList(dto));
        metricService.save(countReceivedTypes(dto));
    }

    private Map<String, Long> countReceivedTypes(List<MessageRabbitDto> dto) {
        Map<String, Long> countByTypes = dto.stream().collect(
                Collectors.groupingBy(d -> MessageType.getTypeById(d.getType()).name(),
                        Collectors.counting()));
        log.info("countReceivedTypes: {}", countByTypes);
        return countByTypes;
    }
}
