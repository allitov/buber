package io.allitov.buber.notification.api.controller;

import io.allitov.buber.notification.api.dto.NotificationTaskRequest;
import io.allitov.buber.notification.api.dto.NotificationTaskResponse;
import io.allitov.buber.notification.api.mapper.NotificationTaskMapper;
import io.allitov.buber.notification.core.service.NotificationTaskService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для управления задачами по уведомлениям.
 */
@Validated
@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationTaskController {

    private final NotificationTaskService notificationTaskService;

    private final NotificationTaskMapper notificationTaskMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTask(@RequestBody @Valid NotificationTaskRequest notificationTaskRequest) {
        notificationTaskService.saveNotificationTask(notificationTaskMapper.toModel(notificationTaskRequest));
    }

    @GetMapping
    public List<NotificationTaskResponse> getNotificationsByTripId(@RequestParam Long tripId) {
        return notificationTaskMapper.toResponseList(notificationTaskService.getNotificationsByTripId(tripId));
    }
}
