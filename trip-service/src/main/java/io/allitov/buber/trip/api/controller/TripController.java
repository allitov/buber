package io.allitov.buber.trip.api.controller;

import io.allitov.buber.trip.api.dto.TripRequest;
import io.allitov.buber.trip.api.dto.TripResponse;
import io.allitov.buber.trip.api.dto.TripStatusUpdateRequest;
import io.allitov.buber.trip.api.mapper.TripMapper;
import io.allitov.buber.trip.core.service.TripService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Контроллер для управления поездками.
 */
@Validated
@RestController
@RequestMapping("/api/v1/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    private final TripMapper tripMapper;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid TripRequest tripRequest) {
        Long tripId = tripService.saveTrip(tripMapper.toModel(tripRequest));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tripId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public List<TripResponse> getPassengerTripsHistory(@RequestParam Long passengerId) {
        return tripMapper.toResponseList(tripService.getPassengerTripsById(passengerId));
    }

    @GetMapping("/{tripId}")
    public TripResponse getTrip(@PathVariable Long tripId) {
        return tripMapper.toResponse(tripService.getTripById(tripId));
    }

    @PatchMapping("/{tripId}/status")
    public ResponseEntity<Void> updateTripStatus(
            @PathVariable Long tripId, @RequestBody @Valid TripStatusUpdateRequest request) {
        tripService.updateTripStatus(tripId, request.status());

        return ResponseEntity.noContent().build();
    }
}
