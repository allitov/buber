package io.allitov.buber.core.service;

import io.allitov.buber.core.exception.EntityNotFoundException;
import io.allitov.buber.core.model.Driver;
import io.allitov.buber.core.model.DriverStatus;
import io.allitov.buber.core.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
     */
    public Long saveDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    /**
     * Поменять статус водителю.
     *
     * @param id        уникальный идентификатор водителя.
     * @param newStatus новый статус водителя.
     */
    public void changeDriverStatusById(Long id, DriverStatus newStatus) {
        driverRepository.updateStatusById(id, newStatus);
    }
}
