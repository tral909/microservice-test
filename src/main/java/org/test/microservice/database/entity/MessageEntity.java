package org.test.microservice.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.test.microservice.en.MessageType;

@Table(name = "message")
@Entity
@Getter
@Setter
public class MessageEntity {

    @Id
    private long id;
    @Column(name = "\"from\"")
    private String from;
    @Column(name = "\"to\"")
    private String to;
    private String text;
    @Enumerated(EnumType.STRING)
    private MessageType type;
}
