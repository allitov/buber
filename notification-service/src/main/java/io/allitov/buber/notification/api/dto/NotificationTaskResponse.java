package io.allitov.buber.notification.api.dto;

import io.allitov.buber.notification.core.model.NotificationTaskStatus;
import io.allitov.buber.notification.core.model.RecipientType;
import lombok.Builder;

/**
 * Информация об уведомлении.
 *
 * @param id            Уникальный идентификатор задачи.
 * @param recipientType Тип получателя (водитель, пассажир).
 * @param recipientId   Уникальный идентификатор получателя.
 * @param message       Содержимое уведомления.
 * @param status        Состояние выполнения задачи.
 */
@Builder(toBuilder = true)
public record NotificationTaskResponse(
        Long id, RecipientType recipientType, Long recipientId, String message, NotificationTaskStatus status) {}
