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

    MessageDto toMessageDto(Message message);

    List<MessageDto> toMessageListDto(List<Message> message);

    Message mapMessageEntity(MessageEntity entity);

    List<Message> mapMessageEntityList(List<MessageEntity> entity);

    MessageEntity mapMessage(Message message);

    List<MessageEntity> mapMessageList(List<Message> message);

    @Mapping(target = "type", expression = "java(org.test.microservice.en.MessageType.getTypeById(dto.getType()))")
    Message mapRabbitDto(MessageRabbitDto dto);

    List<Message> mapRabbitDtoList(List<MessageRabbitDto> dtos);
}
