package io.allitov.buber.notification.core.model;

import java.time.Instant;
import lombok.Builder;

/**
 * Сущность, содержащая информацию о задаче на уведомление пользователя.
 *
 * @param id            Уникальный идентификатор задачи.
 * @param tripId        Уникальный идентификатор поездки.
 * @param recipientType Тип получателя (водитель, пассажир).
 * @param recipientId   Уникальный идентификатор получателя.
 * @param message       Содержимое уведомления.
 * @param status        Состояние выполнения задачи.
 * @param attempts      Количество попыток отправки уведомления.
 * @param createdAt     Время создания задачи.
 */
@Builder(toBuilder = true)
public record NotificationTask(
        Long id,
        Long tripId,
        RecipientType recipientType,
        Long recipientId,
        String message,
        NotificationTaskStatus status,
        Integer attempts,
        Instant createdAt) {}
