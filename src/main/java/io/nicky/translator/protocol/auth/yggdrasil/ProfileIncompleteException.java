package io.nicky.auth.yggdrasil;

/**
 * The type Profile incomplete exception.
 */
public class ProfileIncompleteException extends RuntimeException
{
    /**
     * Instantiates a new Profile incomplete exception.
     */
    public ProfileIncompleteException() {
        super();
    }

    /**
     * Instantiates a new Profile incomplete exception.
     *
     * @param message the message
     */
    public ProfileIncompleteException(final String message) {
        super(message);
    }

    /**
     * Instantiates a new Profile incomplete exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ProfileIncompleteException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Profile incomplete exception.
     *
     * @param cause the cause
     */
    public ProfileIncompleteException(final Throwable cause) {
        super(cause);
    }
}
