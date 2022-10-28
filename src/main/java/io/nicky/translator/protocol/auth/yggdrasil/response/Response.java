package io.nicky.auth.yggdrasil.response;

/**
 * The type Response.
 */
public class Response
{
    private String error;
    private String errorMessage;
    private String cause;

    /**
     * Gets error.
     *
     * @return the error
     */
    public String getError() {
        return this.error;
    }

    /**
     * Gets cause.
     *
     * @return the cause
     */
    public String getCause() {
        return this.cause;
    }

    /**
     * Gets error message.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return this.errorMessage;
    }

    /**
     * Sets error.
     *
     * @param error the error
     */
    protected void setError(final String error) {
        this.error = error;
    }

    /**
     * Sets error message.
     *
     * @param errorMessage the error message
     */
    protected void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Sets cause.
     *
     * @param cause the cause
     */
    protected void setCause(final String cause) {
        this.cause = cause;
    }
}
