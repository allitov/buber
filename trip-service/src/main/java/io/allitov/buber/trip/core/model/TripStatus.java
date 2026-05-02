package io.allitov.buber.trip.core.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Текущий статус поездки в системе.
 */
@Getter
@RequiredArgsConstructor
public enum TripStatus {

    /**
     * Заказ на поездку зарегистрирован в системе.
     */
    CREATED("created"),

    /**
     * Водитель принял заказ.
     */
    ACCEPTED("accepted"),

    /**
     * Поездка началась.
     */
    STARTED("started"),

    /**
     * Поездка завершилась.
     */
    COMPLETED("completed"),

    /**
     * Поездка отменена.
     */
    CANCELLED("cancelled");

    private final String value;
}
