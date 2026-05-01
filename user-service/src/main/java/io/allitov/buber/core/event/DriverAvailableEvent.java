package io.allitov.buber.core.event;

import java.time.Instant;
import lombok.Builder;

/**
 * Событие об обновлении статуса водителя на "доступен".
 *
 * @param driverId  Уникальный идентификатор водителя.
 * @param updatedAt Время обновления статуса.
 */
@Builder(toBuilder = true)
public record DriverAvailableEvent(Long driverId, Instant updatedAt) {}
