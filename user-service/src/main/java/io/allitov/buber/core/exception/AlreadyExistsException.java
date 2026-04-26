package io.allitov.buber.core.exception;

/**
 * Исключение о том, что сущность уже существует.
 */
public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(String message) {
        super(message);
    }
}
