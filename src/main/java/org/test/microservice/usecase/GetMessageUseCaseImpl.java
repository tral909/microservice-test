package org.test.microservice.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.test.microservice.database.repository.MessageRepository;
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
        return messageMapper.mapMessageList(messageRepository.findAll());
    }

    @Override
    public Message getById(int id) {
        return null;
    }
}
