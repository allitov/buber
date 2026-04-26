package io.allitov.buber.api.dto.passenger;

import java.time.Instant;
import lombok.Builder;

/**
 * Информация о пассажире.
 *
 * @param id           Уникальный идентификатор пассажира.
 * @param name         ФИО пассажира.
 * @param email        Электронная почта пассажира.
 * @param phone        Номер телефона пассажира.
 * @param registeredAt Дата регистрации пассажира.
 */
@Builder(toBuilder = true)
public record PassengerResponse(Long id, String name, String email, String phone, Instant registeredAt) {}
