package io.allitov.buber.trip.core.listener;

import io.allitov.buber.common.event.DriverAssignedEvent;
import io.allitov.buber.trip.core.exception.EntityNotFoundException;
import io.allitov.buber.trip.core.service.TripService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Слушатель событий о назначении водителя на поездку.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DriverAssignedListener {

    private final TripService tripService;

    @KafkaListener(
            topics = "${app.event.topic.driver-assigned-events}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    public void handleDriverAssigned(DriverAssignedEvent event) {
        log.info("Received driver assigned event: {}.", event);

        try {
            tripService.acceptTrip(event.tripId(), event.driverId());
        } catch (EntityNotFoundException e) {
            // Нельзя ломаться, если не нашли поездку.
            log.warn(e.getMessage());
        }
    }
}
