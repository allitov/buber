package io.allitov.buber.user.core.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Текущий статус водителя в системе.
 */
@Getter
@RequiredArgsConstructor
public enum DriverStatus {

    /**
     * Водитель готов взять заказ.
     */
    AVAILABLE("available"),

    /**
     * Водитель выполняет заказ.
     */
    BUSY("busy"),

    /**
     * Водитель не в сети или не готов принимать заказы.
     */
    OFFLINE("offline");

    private final String status;
}
