package io.allitov.buber.core.model;

import java.time.Instant;
import lombok.Builder;

/**
 * Сущность, содержащая информацию о пассажире.
 *
 * @param id           Уникальный идентификатор пассажира.
 * @param name         ФИО пассажира.
 * @param email        Электронная почта пассажира.
 * @param phone        Номер телефона пассажира.
 * @param registeredAt Дата регистрации пассажира.
 */
@Builder(toBuilder = true)
public record Passenger(Long id, String name, String email, String phone, Instant registeredAt) {}
