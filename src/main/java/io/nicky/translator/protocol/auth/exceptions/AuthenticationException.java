package io.nicky.auth.exceptions;

/**
 * The type Authentication exception.
 */
public class AuthenticationException extends Exception
{
    /**
     * Instantiates a new Authentication exception.
     */
    public AuthenticationException() {
        super();
    }

    /**
     * Instantiates a new Authentication exception.
     *
     * @param message the message
     */
    public AuthenticationException(final String message) {
        super(message);
    }

    /**
     * Instantiates a new Authentication exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public AuthenticationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Authentication exception.
     *
     * @param cause the cause
     */
    public AuthenticationException(final Throwable cause) {
        super(cause);
    }
}
