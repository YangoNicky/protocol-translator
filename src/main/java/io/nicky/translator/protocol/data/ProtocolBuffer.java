package io.nicky.translator.protocol.data;

import java.nio.ByteBuffer;

public class ProtocolBuffer {

    private final byte[] internal;
    private int writePosition;
    private int readPosition;

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

        this.internal[writePosition++] = value;
    }

    public byte readByte() {
        this.checkCapacity(Action.READ, 1);

        return internal[readPosition++];
    }

    // should not use such weird ints xd
    @Deprecated
    public void write127Int(final int value) {
        if (value > 127 || value < -127)
            throw new IllegalStateException("int is to big or small");

        this.writeByte((byte) value);
    }

    // should not use such weird ints xd
    @Deprecated
    public int read127Int() {
        return this.readByte();
    }

    public void writeByteArray(final byte... bytes) {

    }
    public void readByteArray(final byte... bytes) {

    }

    public void checkCapacity(final Action action, final int capacity) {
        switch (action) {
            case READ -> {
                if (this.readPosition + capacity > internal.length)
                    throw new IllegalStateException("cant read %s bytes! index at 0"
                            .formatted(capacity));
            }
            case WRITE -> {
                if (this.writePosition + capacity > internal.length)
                    throw new IllegalStateException("cant write %s bytes! index at %s max cap %s"
                            .formatted(capacity, writePosition, internal.length));
            }
        }
    }

    public enum Action {
        READ, WRITE
    }

    public byte[] getInternal() {
        return internal;
    }

    public int getWritePosition() {
        return writePosition;
    }

    public void setWritePosition(int writePosition) {
        this.writePosition = writePosition;
    }

    public int getReadPosition() {
        return readPosition;
    }

    public void setReadPosition(int readPosition) {
        this.readPosition = readPosition;
    }
}
