package io.allitov.buber.trip.core.model;

import java.time.Instant;

/**
 * Сущность, содержащая информацию о поездке.
 *
 * @param id          Уникальный идентификатор поездки.
 * @param passengerId Уникальный идентификатор пассажира.
 * @param driverId    Уникальный идентификатор водителя.
 * @param status      Текущий статус поездки.
 * @param origin      Адрес отправления.
 * @param destination Адрес назначения.
 * @param price       Стоимость поездки в копейках.
 * @param createdAt   Время создания записи.
 * @param updatedAt   Время последнего обновления записи.
 */
public record Trip(
        Long id,
        Long passengerId,
        Long driverId,
        TripStatus status,
        String origin,
        String destination,
        Long price,
        Instant createdAt,
        Instant updatedAt) {}
