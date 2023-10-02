package org.test.microservice.scheduler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.test.library.CalculateService;
import org.test.microservice.database.entity.CustomMessageEntity;
import org.test.microservice.en.MessageType;
import org.test.microservice.usecase.GetMessageUseCase;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class MessageStatisticExportScheduler {

    @Value("${message.statistic.export.file}")
    private String filePath;
    private final ObjectMapper objectMapper;
    private final CalculateService calculateService;
    private final GetMessageUseCase getMessageUseCase;

    @Scheduled(cron = "${message.statistic.export.cron}")
    public void runJob() {
        List<CustomMessageEntity> statistic = exportStatisticToJsonFile();
        logNotSmsMessagesCount(statistic);
    }

    @SneakyThrows
    private List<CustomMessageEntity> exportStatisticToJsonFile() {
        List<CustomMessageEntity> statistic = new ArrayList<>();
        for (MessageType type : MessageType.values()) {
            statistic.add(new CustomMessageEntity(type, getMessageUseCase.countByType(type)));
        }
        String json = objectMapper.writeValueAsString(statistic);
        Files.writeString(Path.of(filePath), json);
        return statistic;
    }

    private void logNotSmsMessagesCount(List<CustomMessageEntity> statistic) {
        long noSmsCount = statistic.stream()
                .filter(st -> st.getType() != MessageType.SMS)
                .map(CustomMessageEntity::getCount)
                .mapToLong(Long::valueOf)
                .sum();
        // calculateService я не буду использовать,
        // потому что там:
        // 1) ошибка: smsCount - allCount, а должно быть наоборот
        // 2) параметры int а лучше чтобы были long
        //long noSmsCount = calculateService.calculateWithoutSms((int) total, (int) sms);

        log.info("Total not sms messages: {}", noSmsCount);
    }
}
