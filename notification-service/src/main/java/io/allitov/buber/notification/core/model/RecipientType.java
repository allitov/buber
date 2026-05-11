package io.allitov.buber.notification.core.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Тип получателя уведомления.
 */
@Getter
@RequiredArgsConstructor
public enum RecipientType {

    /**
     * Получатель - водитель.
     */
    DRIVER("driver"),

    /**
     * Получатель - пассажир.
     */
    PASSENGER("passenger");

    private final String value;
}
