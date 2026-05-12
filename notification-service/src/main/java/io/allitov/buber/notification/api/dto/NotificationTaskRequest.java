package io.allitov.buber.notification.api.dto;

import io.allitov.buber.notification.core.model.RecipientType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

/**
 * Данные об уведомлении для последующей отправки пользователю.
 *
 * @param tripId        Уникальный идентификатор поездки.
 * @param recipientType Тип получателя (водитель, пассажир).
 * @param recipientId   Уникальный идентификатор получателя.
 * @param message       Содержимое уведомления.
 */
@Builder(toBuilder = true)
public record NotificationTaskRequest(
        @NotNull Long tripId,
        @NotNull RecipientType recipientType,
        @NotNull Long recipientId,
        @NotBlank @Size(max = 255) String message) {}
