package org.test.microservice.usecase;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.test.microservice.database.repository.MessageRepository;
import org.test.microservice.mapper.MessageMapper;
import org.test.microservice.usecase.model.Message;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaveMessageUseCaseImpl implements SaveMessageUseCase {

    private final MessageMapper messageMapper;
    private final MessageRepository messageRepository;

    @Override
    public void save(Message message) {
        messageRepository.save(messageMapper.mapMessage(message));
    }

    @Override
    public void saveAll(@NotNull List<Message> messageList) {
        messageRepository.saveAll(messageMapper.mapMessageList(messageList));
    }
}
