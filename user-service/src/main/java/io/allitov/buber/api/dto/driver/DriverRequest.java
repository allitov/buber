package io.allitov.buber.api.dto.driver;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

/**
 * Данные о водителе для регистрации.
 *
 * @param name          ФИО водителя.
 * @param email         Электронная почта водителя.
 * @param phone         Номер телефона водителя.
 * @param licenseNumber Номер лицензии водителя.
 */
@Builder(toBuilder = true)
public record DriverRequest(
        @NotBlank @Size(min = 1, max = 255) String name,
        @Size(min = 1, max = 255) String email,
        @NotBlank @Size(min = 1, max = 20) String phone,
        @NotBlank @Size(min = 1, max = 50) String licenseNumber) {}
