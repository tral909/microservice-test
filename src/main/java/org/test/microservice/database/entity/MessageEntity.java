package org.test.microservice.database.entity;

import jakarta.persistence.*;
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
