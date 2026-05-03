package io.allitov.buber.user.api.controller;

import io.allitov.buber.user.api.dto.driver.DriverChangeStatusRequest;
import io.allitov.buber.user.api.dto.driver.DriverRequest;
import io.allitov.buber.user.api.dto.driver.DriverResponse;
import io.allitov.buber.user.api.mapper.DriverMapper;
import io.allitov.buber.user.core.service.DriverService;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для управления водителями.
 */
@Validated
@RestController
@RequestMapping("/api/v1/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    private final DriverMapper driverMapper;

    @GetMapping("/{driverId}")
    public DriverResponse getDriver(@PathVariable Long driverId) {
        return driverMapper.toResponse(driverService.getDriverById(driverId));
    }

    @PostMapping
    public ResponseEntity<Void> createDriver(@RequestBody @Valid DriverRequest request) {
        Long id = driverService.saveDriver(driverMapper.toModel(request));

        return ResponseEntity.created(URI.create("/api/v1/drivers/" + id)).build();
    }

    @PatchMapping("/{driverId}/status")
    public ResponseEntity<Void> updateDriverStatus(
            @PathVariable Long driverId, @RequestBody @Valid DriverChangeStatusRequest request) {
        driverService.updateDriverStatus(driverId, request.status());

        return ResponseEntity.noContent().build();
    }
}
