package io.allitov.buber.trip.api.dto;

import io.allitov.buber.trip.core.model.TripStatus;
import jakarta.validation.constraints.NotNull;

/**
 * Данные о новом статусе поездки.
 *
 * @param status Новый статус поездки.
 */
public record TripStatusUpdateRequest(
        @NotNull(message = "New status must not be null.") TripStatus status) {}
