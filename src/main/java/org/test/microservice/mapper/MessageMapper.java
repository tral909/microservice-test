package org.test.microservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.test.microservice.api.dto.MessageDto;
import org.test.microservice.database.entity.MessageEntity;
import org.test.microservice.rabbit.dto.MessageRabbitDto;
import org.test.microservice.usecase.model.Message;

import java.util.List;

@Mapper
public interface MessageMapper {

    MessageDto map(Message message);

    Message mapMessage(MessageEntity entity);

    List<Message> mapMessageList(List<MessageEntity> entity);

    @Mapping(target = "type", expression = "java(org.test.microservice.en.MessageType.getTypeById(dto.getType()))")
    MessageEntity mapRabbitDto(MessageRabbitDto dto);

    List<MessageEntity> mapRabbitDtoList(List<MessageRabbitDto> dtos);
}
