package io.allitov.buber.core.service;

import io.allitov.buber.core.exception.EntityNotFoundException;
import io.allitov.buber.core.model.Passenger;
import io.allitov.buber.core.repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Сервисный класс для работы со сущностью {@link Passenger}.
 */
@Service
@RequiredArgsConstructor
public class PassengerService {

    private final PassengerRepository passengerRepository;

    /**
     * Получить информацию о пассажире по уникальному идентификатору.
     * @param id уникальный идентификатор, по которому осуществляется поиск.
     * @return {@link Passenger}.
     * @throws EntityNotFoundException если пассажир не был найден.
     */
    public Passenger getPassengerById(Long id) {
        return passengerRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Passenger with id='%d' not found.".formatted(id)));
    }

    /**
     * Сохранить информацию о пассажире.
     * @param passenger информация, которую нужно сохранить.
     * @return уникальный идентификатор сохраненного пассажира.
     */
    public Long savePassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }
}
