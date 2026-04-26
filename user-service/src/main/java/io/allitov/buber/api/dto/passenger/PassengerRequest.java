package io.allitov.buber.api.dto.passenger;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

/**
 * Данные о пассажире для регистрации.
 *
 * @param name  ФИО пассажира.
 * @param email Электронная почта пассажира.
 * @param phone Номер телефона пассажира.
 */
@Builder(toBuilder = true)
public record PassengerRequest(
        @NotBlank @Size(min = 1, max = 255) String name,
        @Size(min = 1, max = 255) String email,
        @NotBlank @Size(min = 1, max = 20) String phone) {}
