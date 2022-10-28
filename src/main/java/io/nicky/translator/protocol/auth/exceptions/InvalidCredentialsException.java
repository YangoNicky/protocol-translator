package io.nicky.auth.exceptions;

import io.nicky.auth.exceptions.AuthenticationException;

/**
 * The type Invalid credentials exception.
 */
public class InvalidCredentialsException extends AuthenticationException
{
    /**
     * Instantiates a new Invalid credentials exception.
     */
    public InvalidCredentialsException() {
        super();
    }

    /**
     * Instantiates a new Invalid credentials exception.
     *
     * @param message the message
     */
    public InvalidCredentialsException(final String message) {
        super(message);
    }

    /**
     * Instantiates a new Invalid credentials exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public InvalidCredentialsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Invalid credentials exception.
     *
     * @param cause the cause
     */
    public InvalidCredentialsException(final Throwable cause) {
        super(cause);
    }
}
