package io.allitov.buber.trip.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Клиент для взаимодействия с user-service.
 */
@FeignClient(
        name = "user-service",
        url = "${client.user-service.host}:${client.user-service.port}",
        configuration = UserServiceClientConfiguration.class)
public interface UserServiceClient {

    /**
     * Проверить существование пассажира по уникальному идентификатору.
     *
     * @param passengerId уникальный идентификатор пассажира.
     * @implNote не возвращает информацию о пассажире. В случае отсутствия пассажира выбрасывает исключение.
     */
    @GetMapping("/api/v1/passengers/{passengerId}")
    void isPassengerExists(@PathVariable Long passengerId);
}
