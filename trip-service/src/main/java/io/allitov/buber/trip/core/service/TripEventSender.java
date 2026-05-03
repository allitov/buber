package io.allitov.buber.trip.core.service;

import io.allitov.buber.trip.api.event.TripCreatedEvent;
import io.allitov.buber.trip.core.model.Trip;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Класс, ответственный за отправку в очередь событий, связанных с поездками.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TripEventSender {

    private final KafkaTemplate<String, TripCreatedEvent> kafkaTemplate;

    @Value("${app.event.topic.trip-created-events}")
    private String tripCreatedTopic;

    /**
     * Отправить событие о создании поездки.
     *
     * @param createdTrip информация о созданной поездке.
     */
    public void sendTripCreatedEvent(Trip createdTrip) {
        TripCreatedEvent event = TripCreatedEvent.builder()
                .tripId(createdTrip.id())
                .passengerId(createdTrip.passengerId())
                .origin(createdTrip.origin())
                .destination(createdTrip.destination())
                .price(createdTrip.price())
                .build();

        kafkaTemplate.send(tripCreatedTopic, createdTrip.id().toString(), event);

        log.info("Sent TripCreatedEvent for tripId='{}' in topic='{}'.", createdTrip.id(), tripCreatedTopic);
    }
}
