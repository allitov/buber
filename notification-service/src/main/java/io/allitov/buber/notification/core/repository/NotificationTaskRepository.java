package io.allitov.buber.notification.core.repository;

import io.allitov.buber.notification.core.model.NotificationTask;
import java.util.List;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 * Репозиторий для взаимодействия с таблицей {@code notification_task}.
 */
@RegisterConstructorMapper(NotificationTask.class)
public interface NotificationTaskRepository {

    /**
     * Получить информацию о всех уведомлениях по поездке по ее уникальному идентификатору.
     *
     * @param tripId уникальный идентификатор поездки.
     * @return список найденных уведомлений. В случае отсутствия записей вернет пустой список.
     */
    @SqlQuery("select * from notification_task where trip_id = :tripId;")
    List<NotificationTask> findAllByTripId(@Bind("tripId") Long tripId);

    /**
     * Добавить задачу по отправке уведомления в базу данных.
     *
     * @param notificationTask сущность с данными для сохранения.
     */
    @SqlUpdate(
            "insert into notification_task (trip_id, recipient_type, recipient_id, message, status) values (:tripId, :recipientType, :recipientId, :message, :status);")
    void save(@BindMethods NotificationTask notificationTask);
}
