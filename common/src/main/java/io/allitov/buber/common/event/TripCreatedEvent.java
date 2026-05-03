package io.allitov.buber.common.event;

import java.time.Instant;
import java.util.UUID;
import lombok.Builder;

/**
 * Событие, публикуемое при успешном создании новой поездки.
 *
 * @param eventId     Уникальный идентификатор события.
 * @param timestamp   Время создания события.
 * @param tripId      Уникальный идентификатор поездки.
 * @param passengerId Уникальный идентификатор пассажира.
 * @param origin      Адрес отправления.
 * @param destination Адрес назначения.
 * @param price       Стоимость поездки в копейках.
 */
@Builder(toBuilder = true)
public record TripCreatedEvent(
        UUID eventId, Instant timestamp, Long tripId, Long passengerId, String origin, String destination, Long price)
        implements DomainEvent {

    @Override
    public String routingKey() {
        return tripId.toString();
    }
}
