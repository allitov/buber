package io.allitov.buber.common.event;

import java.time.Instant;
import java.util.UUID;

/**
 * Общий интерфейс для всех доменных событий системы.
 */
public interface DomainEvent {

    /**
     * Получить уникальный идентификатор события.
     * @return UUID события.
     */
    UUID eventId();

    /**
     * Получить время создания события.
     * @return время создания события.
     */
    Instant timestamp();

    /**
     * Получить ключ партиционирования Kafka.
     * @return ключ партиционирования.
     */
    String routingKey();
}
