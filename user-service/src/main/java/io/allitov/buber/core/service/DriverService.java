package io.allitov.buber.core.service;

import io.allitov.buber.core.exception.AlreadyExistsException;
import io.allitov.buber.core.exception.EntityNotFoundException;
import io.allitov.buber.core.model.Driver;
import io.allitov.buber.core.model.DriverStatus;
import io.allitov.buber.core.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
}
