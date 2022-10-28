package io.nicky.translator.protocol.data;

import java.nio.ByteBuffer;

public class ProtocolBuffer {

    private final byte[] internal;
    private int position;

    public ProtocolBuffer(byte[] internal) {
        this.internal = internal;
    }

    public ProtocolBuffer(final ByteBuffer buffer) {
        this.internal = buffer.array();
    }

    public ProtocolBuffer() {
        this.internal = new byte[1024 * 8];
    }

    public ProtocolBuffer(final int capacity) {
        this.internal = new byte[capacity];
    }

    public void writeByte(final byte value) {
        this.checkCapacity(Action.WRITE, 1);

        this.internal[position++] = value;
    }

    public byte readByte() {
        this.checkCapacity(Action.READ, 1);

        return internal[position--];
    }

    public void checkCapacity(final Action action, final int capacity) {
        switch (action) {
            case READ -> {
                if (this.position - capacity < 0)
                    throw new IllegalStateException("cant read %s bytes! index at 0"
                            .formatted(capacity));
            }
            case WRITE -> {
                if (this.position + capacity > internal.length)
                    throw new IllegalStateException("cant write %s bytes! index at %s max cap %s"
                            .formatted(capacity, position, internal.length));
            }
        }
    }

    public enum Action {

        READ, WRITE

    }

}
