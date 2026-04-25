package io.allitov.buber.core.repository;

import io.allitov.buber.core.model.Passenger;
import java.util.Optional;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 * Репозиторий для взаимодействия с таблицей {@code passenger}.
 */
@RegisterConstructorMapper(Passenger.class)
public interface PassengerRepository {

    /**
     * Получить информацию о пассажире по уникальному идентификатору.
     *
     * @param id уникальный идентификатор пассажира.
     * @return {@link Passenger}.
     */
    @SqlQuery("select * from passenger where id = :id;")
    Optional<Passenger> findById(@Bind("id") Long id);

    /**
     * Создать запись {@link Passenger} в таблице.
     *
     * @param passenger сущность с данными для сохранения.
     * @return уникальный идентификатор созданной записи.
     */
    @SqlUpdate("insert into passenger (name, email, phone) values (:name, :email, :phone);")
    @GetGeneratedKeys
    Long save(@BindBean Passenger passenger);
}
