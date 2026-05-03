package io.allitov.buber.user.core.exception;

/**
 * Исключение о том, что сущность не была найдена.
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }
}
