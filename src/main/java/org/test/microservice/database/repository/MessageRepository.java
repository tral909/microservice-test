package org.test.microservice.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.microservice.database.entity.MessageEntity;
import org.test.microservice.en.MessageType;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    List<MessageEntity> findByType(MessageType type);

    long countByType(MessageType type);
}
