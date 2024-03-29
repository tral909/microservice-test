package org.test.microservice.api.presenter;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.test.microservice.en.MessageType;
import org.test.microservice.api.dto.MessageDto;

import java.util.List;

public interface MessagePresenter {

    @NotNull
    Page<MessageDto> getAll(Pageable pageable);

    @NotNull
    MessageDto getById(long id);

    @NotNull
    List<MessageDto> getByType(@NotNull MessageType type);
}
