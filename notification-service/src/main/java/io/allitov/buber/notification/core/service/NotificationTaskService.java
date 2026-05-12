package io.allitov.buber.notification.core.service;

import io.allitov.buber.notification.core.model.NotificationTask;
import io.allitov.buber.notification.core.model.NotificationTaskStatus;
import io.allitov.buber.notification.core.repository.NotificationTaskRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы со сущностью {@link NotificationTask}.
 */
@Service
@RequiredArgsConstructor
public class NotificationTaskService {

    private final NotificationTaskRepository notificationTaskRepository;

    /**
     * Получить список уведомлений по поездке.
     *
     * @param tripId уникальный идентификатор поездки.
     * @return список записей с информацией об уведомлениях. Список может быть пустым.
     */
    public List<NotificationTask> getNotificationsByTripId(Long tripId) {
        return notificationTaskRepository.findAllByTripId(tripId);
    }

    /**
     * Сохранить задачу по отправке уведомления.
     *
     * @param notificationTask информация, которую нужно сохранить.
     */
    public void saveNotificationTask(NotificationTask notificationTask) {
        NotificationTask taskToSave = notificationTask.toBuilder()
                .status(NotificationTaskStatus.PENDING)
                .build();
        notificationTaskRepository.save(taskToSave);
    }
}
