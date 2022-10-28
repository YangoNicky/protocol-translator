package io.nicky.translator.protocol.units;

public final class DebugLogger {

    public static final DebugLogger INTERNAL = new DebugLogger();

    private boolean enabled = true;

    public void debug(final String message) {
        if (this.isEnabled())
            System.err.printf("[%s]: %s%n", Thread.currentThread().getName(), message);
    }
    public void debug(final String message, Object... arguments) {
        if (this.isEnabled())
            System.err.printf("[%s]: %s%n", Thread.currentThread().getName(), message.formatted(arguments));
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
