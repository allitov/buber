package io.allitov.buber.configuration.jdbi;

import io.allitov.buber.core.repository.DriverRepository;
import io.allitov.buber.core.repository.PassengerRepository;
import org.jdbi.v3.core.Jdbi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация репозиториев, использующих JDBI.
 */
@Configuration
public class JdbiRepositoryConfiguration {

    @Bean
    PassengerRepository passengerRepository(Jdbi jdbi) {
        return jdbi.onDemand(PassengerRepository.class);
    }

    @Bean
    DriverRepository driverRepository(Jdbi jdbi) {
        return jdbi.onDemand(DriverRepository.class);
    }
}
