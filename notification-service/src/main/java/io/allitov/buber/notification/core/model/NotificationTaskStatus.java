package io.allitov.buber.notification.core.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Статус задачи на отправку уведомления.
 */
@Getter
@RequiredArgsConstructor
public enum NotificationTaskStatus {

    /**
     * Задача создана и уведомление ожидает отправки.
     */
    PENDING("pending"),

    /**
     * Уведомление успешно отправлено.
     */
    SENT("sent"),

    /**
     * Уведомление ожидает повторной отправки.
     */
    RETRY("retry"),

    /**
     * Уведомление не удалось отправить.
     */
    FAILED("failed");

    private final String value;
}
