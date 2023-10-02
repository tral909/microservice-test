package org.test.microservice.usecase;

import org.test.microservice.en.MessageType;
import org.test.microservice.usecase.model.Message;

import java.util.List;

public interface GetMessageUseCase {

    List<Message> getAll();

    List<Message> getByType(MessageType type);

    Message getById(long id);
}
