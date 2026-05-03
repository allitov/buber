package io.allitov.buber.trip.api.event;

import lombok.Builder;

/**
 * Событие, публикуемое при успешном создании новой поездки.
 *
 * @param tripId      Уникальный идентификатор поездки.
 * @param passengerId Уникальный идентификатор пассажира.
 * @param origin      Адрес отправления.
 * @param destination Адрес назначения.
 * @param price       Стоимость поездки в копейках.
 */
@Builder(toBuilder = true)
public record TripCreatedEvent(Long tripId, Long passengerId, String origin, String destination, Long price) {}
