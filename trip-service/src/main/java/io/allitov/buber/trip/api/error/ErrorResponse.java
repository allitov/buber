package io.allitov.buber.trip.api.error;

import lombok.Builder;

/**
 * Данные об ошибке.
 *
 * @param code    Код ошибки.
 * @param message Информация об ошибке.
 */
@Builder(toBuilder = true)
public record ErrorResponse(Integer code, String message) {}
