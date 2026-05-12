package io.allitov.buber.notification.configuration.repository;

import io.allitov.buber.notification.core.repository.NotificationTaskRepository;
import org.jdbi.v3.core.Jdbi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация репозиториев, использующих {@code JDBI}.
 */
@Configuration
class JdbiRepositoryConfiguration {

    @Bean
    NotificationTaskRepository notificationTaskRepository(Jdbi jdbi) {
        return jdbi.onDemand(NotificationTaskRepository.class);
    }
}
