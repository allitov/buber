package io.allitov.buber.core.model;

import java.time.Instant;
import lombok.Builder;

/**
 * Сущность, содержащая информацию о водителе.
 *
 * @param id            Уникальный идентификатор водителя.
 * @param name          ФИО водителя.
 * @param email         Электронная почта водителя.
 * @param phone         Номер телефона водителя.
 * @param licenseNumber Номер лицензии водителя.
 * @param status        Текущий статус водителя в системе.
 * @param registeredAt  Дата регистрации водителя.
 */
@Builder(toBuilder = true)
public record Driver(
        Long id,
        String name,
        String email,
        String phone,
        String licenseNumber,
        DriverStatus status,
        Instant registeredAt) {}
