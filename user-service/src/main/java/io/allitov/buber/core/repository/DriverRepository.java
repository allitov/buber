package io.allitov.buber.core.repository;

import io.allitov.buber.core.model.Driver;
import io.allitov.buber.core.model.DriverStatus;
import java.util.Optional;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 * Репозиторий для взаимодействия с таблицей {@code driver}.
 */
@RegisterConstructorMapper(Driver.class)
public interface DriverRepository {

    /**
     * Получить информацию о водителе по уникальному идентификатору.
     *
     * @param id уникальный идентификатор водителя.
     * @return {@link Driver}.
     */
    @SqlQuery("select * from driver where id = :id;")
    Optional<Driver> findById(@Bind("id") Long id);

    /**
     * Создать запись о водителе в таблице.
     *
     * @param driver сущность с данными для сохранения.
     * @return уникальный идентификатор созданной записи.
     */
    @SqlUpdate(
            "insert into driver (name, email, phone, license_number, status) values (:name, :email, :phone, :licenseNumber, :status);")
    @GetGeneratedKeys
    Long save(@BindBean Driver driver);

    /**
     * Обновить статус водителя в таблице.
     *
     * @param id        уникальный идентификатор водителя.
     * @param newStatus новый статус, который должен быть записан.
     */
    @SqlUpdate("update driver set status = :newStatus where id = :id;")
    void updateStatusById(@Bind("id") Long id, @Bind("newStatus") DriverStatus newStatus);
}
