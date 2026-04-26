package io.allitov.buber.core.repository;

import io.allitov.buber.core.model.Driver;
import io.allitov.buber.core.model.DriverStatus;
import java.util.Optional;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
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
    Long save(@BindMethods Driver driver);

    /**
     * Обновить статус водителя в таблице.
     *
     * @param id        уникальный идентификатор водителя.
     * @param newStatus новый статус, который должен быть записан.
     */
    @SqlUpdate("update driver set status = :newStatus where id = :id;")
    void updateStatusById(@Bind("id") Long id, @Bind("newStatus") DriverStatus newStatus);

    /**
     * Проверить существование записи по номеру телефона или номеру лицензии.
     *
     * @param phone         номер телефона, по которому осуществляется поиск.
     * @param licenseNumber номер лицензии, по которому осуществляется поиск.
     * @return {@code true} - если найдена запись по номеру телефона или по номеру лицензии; иначе {@code false}.
     */
    @SqlQuery("select exists(select 1 from driver where phone = :phone or license_number = :licenseNumber);")
    boolean existsByPhoneOrLicenseNumber(@Bind("phone") String phone, @Bind("licenseNumber") String licenseNumber);
}
