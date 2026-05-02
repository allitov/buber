package io.allitov.buber.trip.core.repository;

import io.allitov.buber.trip.core.model.Trip;
import io.allitov.buber.trip.core.model.TripStatus;
import java.util.List;
import java.util.Optional;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 * Репозиторий для взаимодействия с таблицей {@code trip}.
 */
@RegisterConstructorMapper(Trip.class)
public interface TripRepository {

    /**
     * Получить информацию о поездке по уникальному идентификатору.
     *
     * @param id уникальный идентификатор поездки.
     * @return {@link Optional} с записью о поездке, если такая существует; иначе пустой {@link Optional}.
     */
    @SqlQuery("select * from trip where id = :id;")
    Optional<Trip> findById(@Bind("id") Long id);

    /**
     * Создать запись о поездке в таблице.
     *
     * @param trip сущность с данными для сохранения.
     * @return уникальный идентификатор созданной записи.
     */
    @SqlUpdate(
            "insert into trip (passenger_id, driver_id, status, origin, destination, price) values (:passengerId, :driverId, :status, :origin, :destination, :price);")
    @GetGeneratedKeys
    Long save(@BindMethods Trip trip);

    /**
     * Получить информацию о всех поездках пассажира по его уникальному идентификатору.
     *
     * @param passengerId уникальный идентификатор пассажира.
     * @return список найденных поездок. В случае отсутствия записей вернет пустой список.
     */
    @SqlQuery("select * from trip where passenger_id = :passengerId;")
    List<Trip> findAllByPassengerId(@Bind("passengerId") Long passengerId);

    /**
     * Обновить статус поездки.
     *
     * @param id        уникальный идентификатор поездки.
     * @param newStatus новый статус, который должен быть записан.
     * @implNote автоматически обновляет поле {@code updated_at} с помощью триггера на уровне БД.
     */
    @SqlUpdate("update trip set status = :newStatus where id = :id;")
    void updateStatusById(@Bind("id") Long id, @Bind("newStatus") TripStatus newStatus);
}
