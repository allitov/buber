package io.allitov.buber.trip.configuration.repository;

import io.allitov.buber.trip.core.repository.TripRepository;
import org.jdbi.v3.core.Jdbi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация репозиториев, использующих JDBI.
 */
@Configuration
class JdbiRepositoryConfiguration {

    @Bean
    TripRepository tripRepository(Jdbi jdbi) {
        return jdbi.onDemand(TripRepository.class);
    }
}
