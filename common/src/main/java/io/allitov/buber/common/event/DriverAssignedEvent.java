package io.allitov.buber.common.event;

import java.time.Instant;
import java.util.UUID;
import lombok.Builder;

/**
 * Событие о назначении водителя на поездку.
 *
 * @param eventId   Уникальный идентификатор события.
 * @param timestamp Время создания события.
 * @param driverId  Уникальный идентификатор водителя.
 * @param tripId    Уникальный идентификатор поездки.
 */
@Builder(toBuilder = true)
public record DriverAssignedEvent(UUID eventId, Instant timestamp, Long driverId, Long tripId) implements DomainEvent {

    @Override
    public String routingKey() {
        return driverId.toString();
    }
}
