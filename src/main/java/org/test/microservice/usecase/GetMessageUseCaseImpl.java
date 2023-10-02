package org.test.microservice.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.test.microservice.database.repository.MessageRepository;
import org.test.microservice.en.MessageType;
import org.test.microservice.mapper.MessageMapper;
import org.test.microservice.usecase.model.Message;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetMessageUseCaseImpl implements GetMessageUseCase {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    @Override
    public List<Message> getAll() {
        return messageMapper.mapMessageEntityList(messageRepository.findAll());
    }

    @Override
    public List<Message> getByType(MessageType type) {
        return messageMapper.mapMessageEntityList(messageRepository.findByType(type));
    }

    @Override
    public Message getById(long id) {
        return messageRepository.findById(id)
                .map(messageMapper::mapMessageEntity)
                .orElseThrow();
    }

    @Override
    public long countByType(MessageType type) {
        return messageRepository.countByType(type);
    }
}
