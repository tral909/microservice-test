package org.test.microservice.en;

import java.util.NoSuchElementException;

public enum MessageType {

    SMS(10),
    EMAIL(20),
    TELEGRAM(30);

    private final int id;

    MessageType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static MessageType getTypeById(int id) {
        for (MessageType type : MessageType.values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        throw new NoSuchElementException("MessageType with such id: " + id + " does not exists");
    }
}
