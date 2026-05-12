package io.allitov.buber.notification.api.mapper;

import io.allitov.buber.notification.api.dto.NotificationTaskRequest;
import io.allitov.buber.notification.api.dto.NotificationTaskResponse;
import io.allitov.buber.notification.core.model.NotificationTask;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Маппер для сущности {@link NotificationTask}.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NotificationTaskMapper {

    /**
     * Создает dto для передачи информации о задаче на уведомление.
     *
     * @param notificationTask сущность, из которой взять данные для формирования dto.
     * @return dto с информацией о задаче.
     */
    NotificationTaskResponse toResponse(NotificationTask notificationTask);

    /**
     * Создает сущность задачи на уведомление из переданного dto.
     *
     * @param notificationTaskRequest dto, из которого взять данные для формирования.
     * @return сущность задачи.
     */
    NotificationTask toModel(NotificationTaskRequest notificationTaskRequest);

    /**
     * Создает список dto для передачи информации о задачах на уведомление.
     *
     * @param notificationTaskList список сущностей, из которых взять данные для формирования списка dto.
     * @return список dto с информацией о задачах.
     */
    List<NotificationTaskResponse> toResponseList(List<NotificationTask> notificationTaskList);
}
