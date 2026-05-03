package io.allitov.buber.user.core.service;

import io.allitov.buber.common.event.DomainEvent;
import io.allitov.buber.common.event.DriverAvailableEvent;
import io.allitov.buber.user.core.exception.AlreadyExistsException;
import io.allitov.buber.user.core.exception.EntityNotFoundException;
import io.allitov.buber.user.core.model.Driver;
import io.allitov.buber.user.core.model.DriverStatus;
import io.allitov.buber.user.core.repository.DriverRepository;
import java.time.Instant;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Сервисный класс для работы со сущностью {@link Driver}.
 */
@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;

    private final KafkaTemplate<String, DomainEvent> kafkaTemplate;

    @Value("${app.event.topic.driver-status-events}")
    private String driverStatusTopic;

    /**
     * Получить информацию о водителе по уникальному идентификатору.
     *
     * @param id уникальный идентификатор, по которому осуществляется поиск.
     * @return {@link Driver}.
     * @throws EntityNotFoundException если водитель не был найден.
     */
    @Cacheable(value = "drivers", key = "#id")
    public Driver getDriverById(Long id) {
        return driverRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Driver with id='%d' not found.".formatted(id)));
    }

    /**
     * Сохранить информацию о водителе.
     *
     * @param driver информация, которую нужно сохранить.
     * @return уникальный идентификатор сохраненного водителя.
     * @throws AlreadyExistsException если номер телефона или номер лицензии водителя уже записаны в системе.
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Long saveDriver(Driver driver) {
        if (driverRepository.existsByPhoneOrLicenseNumber(driver.phone(), driver.licenseNumber())) {
            throw new AlreadyExistsException("Driver with phone='%s' or license number='%s' already exists."
                    .formatted(driver.name(), driver.licenseNumber()));
        }

        Driver driverToSave = driver.toBuilder().status(DriverStatus.OFFLINE).build();

        return driverRepository.save(driverToSave);
    }

    /**
     * Поменять статус водителю.
     *
     * @param id        уникальный идентификатор водителя.
     * @param newStatus новый статус водителя.
     * @implNote Отправляет событие в очередь сообщений о доступности водителя,
     * если новый статус {@code DriverStatus.AVAILABLE}.
     */
    @CacheEvict(value = "drivers", key = "#id")
    public void updateDriverStatus(Long id, DriverStatus newStatus) {
        driverRepository.updateStatusById(id, newStatus);
        DriverAvailableEvent event = DriverAvailableEvent.builder()
                .eventId(UUID.randomUUID())
                .timestamp(Instant.now())
                .driverId(id)
                .updatedAt(Instant.now())
                .build();

        if (newStatus == DriverStatus.AVAILABLE) {
            kafkaTemplate.send(driverStatusTopic, event.routingKey(), event);
        }
    }
}
