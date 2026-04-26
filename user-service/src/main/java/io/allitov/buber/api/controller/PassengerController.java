package io.allitov.buber.api.controller;

import io.allitov.buber.api.dto.passenger.PassengerRequest;
import io.allitov.buber.api.dto.passenger.PassengerResponse;
import io.allitov.buber.api.mapper.PassengerMapper;
import io.allitov.buber.core.service.PassengerService;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для управления пассажирами.
 */
@Validated
@RestController
@RequestMapping("/api/v1/passengers")
@RequiredArgsConstructor
public class PassengerController {

    private final PassengerService passengerService;

    private final PassengerMapper passengerMapper;

    @GetMapping("/{passengerId}")
    public PassengerResponse getPassenger(@PathVariable Long passengerId) {
        return passengerMapper.toResponse(passengerService.getPassengerById(passengerId));
    }

    @PostMapping
    public ResponseEntity<Void> registerPassenger(@RequestBody @Valid PassengerRequest request) {
        Long id = passengerService.savePassenger(passengerMapper.toModel(request));

        return ResponseEntity.created(URI.create("/api/v1/passengers/" + id)).build();
    }
}
