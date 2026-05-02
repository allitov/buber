package io.allitov.buber.trip.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

/**
 * Данные о поездке для регистрации.
 *
 * @param passengerId Уникальный идентификатор пассажира.
 * @param origin      Адрес отправления.
 * @param destination Адрес назначения.
 */
@Builder(toBuilder = true)
public record TripRequest(
        @NotNull(message = "Passenger ID must not be null.") Long passengerId,

        @NotBlank(message = "Origin address must not be blank.")
        @Size(max = 255, message = "Origin address must not exceed 255 characters.")
        String origin,

        @NotBlank(message = "Destination address must not be blank.")
        @Size(max = 255, message = "Destination address must not exceed 255 characters.")
        String destination) {}
