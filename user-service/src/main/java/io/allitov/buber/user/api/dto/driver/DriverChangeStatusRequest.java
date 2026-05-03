package io.allitov.buber.user.api.dto.driver;

import io.allitov.buber.user.core.model.DriverStatus;
import lombok.Builder;

/**
 * Данные о новом статусе водителя.
 *
 * @param status Новый статус водителя.
 */
@Builder(toBuilder = true)
public record DriverChangeStatusRequest(DriverStatus status) {}
