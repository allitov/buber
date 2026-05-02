package io.allitov.buber.trip.client;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

/**
 * Конфигурация для user-service клиента.
 */
class UserServiceClientConfiguration {

    @Bean
    ErrorDecoder userServiceErrorDecoder() {
        return new UserServiceErrorDecoder();
    }
}
