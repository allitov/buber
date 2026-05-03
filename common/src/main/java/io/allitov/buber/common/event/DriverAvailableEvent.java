package io.allitov.buber.common.event;

import java.time.Instant;
import java.util.UUID;
import lombok.Builder;

/**
 * Событие об обновлении статуса водителя на "доступен".
 *
 * @param eventId   Уникальный идентификатор события.
 * @param timestamp Время создания события.
 * @param driverId  Уникальный идентификатор водителя.
 * @param updatedAt Время обновления статуса.
 */
@Builder(toBuilder = true)
public record DriverAvailableEvent(UUID eventId, Instant timestamp, Long driverId, Instant updatedAt)
        implements DomainEvent {

    @Override
    public String routingKey() {
        return driverId.toString();
    }
}
