package org.test.microservice.api.presenter;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.test.microservice.en.MessageType;
import org.test.microservice.api.dto.MessageDto;
import org.test.microservice.mapper.MessageMapper;
import org.test.microservice.usecase.GetMessageUseCase;
import org.test.microservice.usecase.model.Message;

import java.util.*;

@Component
@RequiredArgsConstructor
@Log4j2
public class MessagePresenterImpl implements MessagePresenter {

    private final GetMessageUseCase getMessageUseCase;
    private final MessageMapper messageMapper;

    @Override
    @NotNull
    public List<MessageDto> getAll() {
        return messageMapper.toMessageListDto(getMessageUseCase.getAll());
    }

    @Override
    @NotNull
    public MessageDto getById(long id) {
        return messageMapper.toMessageDto(getMessageUseCase.getById(id));
    }

    @Override
    @NotNull
    public List<MessageDto> getByType(@NotNull MessageType type) {
        List<Message> messages = getMessageUseCase.getByType(type);
        log.debug("Message count {}", messages.size());
        return messageMapper.toMessageListDto(messages);
    }
}
