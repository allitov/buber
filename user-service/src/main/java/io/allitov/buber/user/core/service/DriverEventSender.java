package io.allitov.buber.user.core.service;

import io.allitov.buber.common.event.DomainEvent;
import io.allitov.buber.common.event.DriverAssignedEvent;
import java.time.Instant;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Класс, ответственный за отправку в очередь событий, связанных с водителями.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DriverEventSender {

    private final KafkaTemplate<String, DomainEvent> kafkaTemplate;

    @Value("${app.event.topic.driver-assigned-events}")
    private String driverAssignedTopic;

    /**
     * Отправить событие о назначении водителя на поездку.
     *
     * @param driverId уникальный идентификатор водителя.
     * @param tripId   уникальный идентификатор поездки.
     */
    public void sendDriverAssignedEvent(Long driverId, Long tripId) {
        DriverAssignedEvent event = DriverAssignedEvent.builder()
                .eventId(UUID.randomUUID())
                .timestamp(Instant.now())
                .driverId(driverId)
                .tripId(tripId)
                .build();

        kafkaTemplate.send(driverAssignedTopic, event.routingKey(), event);

        log.info("Sent DriverAssignedEvent for driverId='{}' in topic='{}'", driverId, driverAssignedTopic);
    }
}
