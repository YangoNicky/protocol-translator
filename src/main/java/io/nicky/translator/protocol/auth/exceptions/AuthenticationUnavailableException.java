package io.nicky.auth.exceptions;

import io.nicky.auth.exceptions.AuthenticationException;

/**
 * The type Authentication unavailable exception.
 */
public class AuthenticationUnavailableException extends AuthenticationException
{
    /**
     * Instantiates a new Authentication unavailable exception.
     */
    public AuthenticationUnavailableException() {
        super();
    }

    /**
     * Instantiates a new Authentication unavailable exception.
     *
     * @param message the message
     */
    public AuthenticationUnavailableException(final String message) {
        super(message);
    }

    /**
     * Instantiates a new Authentication unavailable exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public AuthenticationUnavailableException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Authentication unavailable exception.
     *
     * @param cause the cause
     */
    public AuthenticationUnavailableException(final Throwable cause) {
        super(cause);
    }
}
