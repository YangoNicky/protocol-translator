package io.nicky.auth.minecraft;

import java.util.UUID;
import io.nicky.auth.GameProfile;
import java.util.Calendar;
import java.util.Date;

/**
 * The type Insecure texture exception.
 */
public class InsecureTextureException extends RuntimeException
{
    /**
     * Instantiates a new Insecure texture exception.
     *
     * @param message the message
     */
    public InsecureTextureException(final String message) {
        super(message);
    }

    /**
     * The type Outdated texture exception.
     */
    public static class OutdatedTextureException extends InsecureTextureException
    {
        private final Date validFrom;
        private final Calendar limit;

        /**
         * Instantiates a new Outdated texture exception.
         *
         * @param validFrom the valid from
         * @param limit     the limit
         */
        public OutdatedTextureException(final Date validFrom, final Calendar limit) {
            super("Decrypted textures payload is too old (" + validFrom + ", but we need it to be at least " + limit + ")");
            this.validFrom = validFrom;
            this.limit = limit;
        }
    }

    /**
     * The type Wrong texture owner exception.
     */
    public static class WrongTextureOwnerException extends InsecureTextureException
    {
        private final GameProfile expected;
        private final UUID resultId;
        private final String resultName;

        /**
         * Instantiates a new Wrong texture owner exception.
         *
         * @param expected   the expected
         * @param resultId   the result id
         * @param resultName the result name
         */
        public WrongTextureOwnerException(final GameProfile expected, final UUID resultId, final String resultName) {
            super("Decrypted textures payload was for another user (expected " + expected.getId() + "/" + expected.getName() + " but was for " + resultId + "/" + resultName + ")");
            this.expected = expected;
            this.resultId = resultId;
            this.resultName = resultName;
        }
    }

    /**
     * The type Missing texture exception.
     */
    public static class MissingTextureException extends InsecureTextureException
    {
        /**
         * Instantiates a new Missing texture exception.
         */
        public MissingTextureException() {
            super("No texture information found");
        }
    }
}
