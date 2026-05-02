package io.allitov.buber.trip.core.service;

import io.allitov.buber.trip.core.exception.EntityNotFoundException;
import io.allitov.buber.trip.core.model.Trip;
import io.allitov.buber.trip.core.model.TripStatus;
import io.allitov.buber.trip.core.repository.TripRepository;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы со сущностью {@link Trip}.
 */
@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;

    /**
     * Получить информацию о поездке по уникальному идентификатору.
     *
     * @param tripId уникальный идентификатор, по которому осуществляется поиск.
     * @return информация о найденной поездке.
     * @throws EntityNotFoundException если поездка не была найдена.
     */
    public Trip getTripById(Long tripId) {
        return tripRepository
                .findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip with tripId='%d' not found.".formatted(tripId)));
    }

    /**
     * Сохранить заявку на поездку.
     *
     * @param trip информация, которую нужно сохранить.
     * @return уникальный идентификатор сохраненной поездки.
     */
    public Long saveTrip(Trip trip) {
        // Для простоты считаем стоимость поездки рандомно.
        // По-хорошему нужно вычислять расстояние от одной точки до другой и умножать на коэффициент.
        // Однако для этого видится разработка еще одного сервиса.
        long randomPrice = ThreadLocalRandom.current().nextLong(10000, 100000);

        trip.toBuilder().status(TripStatus.CREATED).price(randomPrice).build();

        return tripRepository.save(trip);
    }

    /**
     * Получить историю поездок пассажира по его уникальному идентификатору.
     *
     * @param passengerId уникальный идентификатор пассажира.
     * @return список записей с информацией о поездках. Список может быть пустым.
     */
    public List<Trip> getPassengerTripsById(Long passengerId) {
        return tripRepository.findAllByPassengerId(passengerId);
    }

    /**
     * Поменять статус поездке по уникальному идентификатору.
     *
     * @param tripId    уникальный идентификатор поездки.
     * @param newStatus новый статус поездки.
     */
    public void updateTripStatus(Long tripId, TripStatus newStatus) {
        tripRepository.updateStatusById(tripId, newStatus);
    }
}
