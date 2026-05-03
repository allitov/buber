package io.allitov.buber.user.api.dto.driver;

import java.time.Instant;
import lombok.Builder;

/**
 * Информация о водителе.
 *
 * @param id            Уникальный идентификатор водителя.
 * @param name          ФИО водителя.
 * @param email         Электронная почта водителя.
 * @param phone         Номер телефона водителя.
 * @param licenseNumber Номер лицензии водителя.
 * @param status        Текущий статус водителя.
 * @param registeredAt  Дата регистрации водителя.
 */
@Builder(toBuilder = true)
public record DriverResponse(
        Long id, String name, String email, String phone, String licenseNumber, String status, Instant registeredAt) {}
