package io.allitov.buber.user.core.listener;

import io.allitov.buber.common.event.TripCreatedEvent;
import io.allitov.buber.user.core.exception.EntityNotFoundException;
import io.allitov.buber.user.core.service.DriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Слушатель событий о создании заказа на поездку.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TripCreatedListener {

    private final DriverService driverService;

    @KafkaListener(
            topics = "${app.event.topic.trip-created-events}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    public void handleTripCreatedEvent(TripCreatedEvent tripCreatedEvent) {
        log.info("Received trip-created event {}.", tripCreatedEvent);

        try {
            driverService.assignDriver(tripCreatedEvent.tripId());
        } catch (EntityNotFoundException _) {
            // Падать нельзя, если не получилось найти водителя.
            log.warn("Driver for trip with id='{}' was not found.", tripCreatedEvent.tripId());
        }
    }
}
