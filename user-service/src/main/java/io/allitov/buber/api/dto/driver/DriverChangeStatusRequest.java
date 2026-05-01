package io.allitov.buber.api.dto.driver;

import io.allitov.buber.core.model.DriverStatus;
import lombok.Builder;

/**
 * Данные о новом статусе водителя.
 *
 * @param status Новый статус водителя.
 */
@Builder(toBuilder = true)
public record DriverChangeStatusRequest(DriverStatus status) {}
