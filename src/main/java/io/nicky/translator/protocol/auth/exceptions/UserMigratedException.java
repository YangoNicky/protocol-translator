package io.nicky.auth.exceptions;

import io.nicky.auth.exceptions.InvalidCredentialsException;

/**
 * The type User migrated exception.
 */
public class UserMigratedException extends InvalidCredentialsException
{
    /**
     * Instantiates a new User migrated exception.
     */
    public UserMigratedException() {
        super();
    }

    /**
     * Instantiates a new User migrated exception.
     *
     * @param message the message
     */
    public UserMigratedException(final String message) {
        super(message);
    }

    /**
     * Instantiates a new User migrated exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public UserMigratedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new User migrated exception.
     *
     * @param cause the cause
     */
    public UserMigratedException(final Throwable cause) {
        super(cause);
    }
}
