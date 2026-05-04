package io.allitov.buber.user.core.service;

import io.allitov.buber.user.core.exception.AlreadyExistsException;
import io.allitov.buber.user.core.exception.EntityNotFoundException;
import io.allitov.buber.user.core.model.Driver;
import io.allitov.buber.user.core.model.DriverStatus;
import io.allitov.buber.user.core.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Сервисный класс для работы со сущностью {@link Driver}.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;

    private final DriverEventSender driverEventSender;

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
     */
    @CacheEvict(value = "drivers", key = "#id")
    public void updateDriverStatus(Long id, DriverStatus newStatus) {
        driverRepository.updateStatusById(id, newStatus);
    }

    /**
     * Назначить водителя на поездку.
     *
     * @param tripId Уникальный идентификатор поездки.
     * @throws EntityNotFoundException если не получилось найти свободного водителя.
     * @implNote отправляет событие в очередь сообщений об успешном назначении водителя.
     */
    @Transactional
    public void assignDriver(Long tripId) {
        // Тут нужно понимать, что водителя можно назначить, если немного подождать.
        // Сейчас сразу падаем и не назначаем водителя для простоты.
        Driver foundDriver = driverRepository
                .findAvailableDriverForUpdate()
                .orElseThrow(() -> new EntityNotFoundException("Driver with status 'AVAILABLE' not found."));
        driverRepository.updateStatusById(foundDriver.id(), DriverStatus.BUSY);

        driverEventSender.sendDriverAssignedEvent(foundDriver.id(), tripId);

        log.info("Driver with id='{}' assigned to trip with id='{}'.", foundDriver.id(), tripId);
    }
}
