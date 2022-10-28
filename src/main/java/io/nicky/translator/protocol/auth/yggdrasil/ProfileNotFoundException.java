package io.nicky.auth.yggdrasil;

/**
 * The type Profile not found exception.
 */
public class ProfileNotFoundException extends RuntimeException
{
    /**
     * Instantiates a new Profile not found exception.
     */
    public ProfileNotFoundException() {
        super();
    }

    /**
     * Instantiates a new Profile not found exception.
     *
     * @param message the message
     */
    public ProfileNotFoundException(final String message) {
        super(message);
    }

    /**
     * Instantiates a new Profile not found exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ProfileNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Profile not found exception.
     *
     * @param cause the cause
     */
    public ProfileNotFoundException(final Throwable cause) {
        super(cause);
    }
}
