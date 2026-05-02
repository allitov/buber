package io.allitov.buber.trip.client;

import feign.Response;
import feign.codec.ErrorDecoder;
import io.allitov.buber.trip.api.error.ErrorResponse;
import io.allitov.buber.trip.core.exception.EntityNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;

/**
 * Реализация {@link ErrorDecoder} для корректных обработок ошибок от {@code user-service}.
 */
@Slf4j
class UserServiceErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        log.warn("Error occurred during Feign call. Method: {}, Status: {}", methodKey, response.status());

        ErrorResponse errorResponse;
        try (InputStream bodyIs = response.body().asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            errorResponse = mapper.readValue(bodyIs, ErrorResponse.class);
        } catch (IOException e) {
            return new UncheckedIOException(e);
        }

        return switch (response.status()) {
            case 400 -> new IllegalArgumentException(errorResponse.message());
            case 404 -> new EntityNotFoundException(errorResponse.message());
            default -> defaultDecoder.decode(methodKey, response);
        };
    }
}
