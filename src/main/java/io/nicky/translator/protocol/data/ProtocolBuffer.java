package io.nicky.translator.protocol.data;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@SuppressWarnings("unused")
public class ProtocolBuffer {

    private final int packetId;

    private byte[] internal;
    private int writePosition;
    private int readPosition;

    public ProtocolBuffer(int packetId, byte[] internal) {
        this.packetId = packetId;
        this.internal = internal;
    }

    public ProtocolBuffer(int packetId, final ByteBuffer buffer) {
        this.packetId = packetId;
        this.internal = buffer.array();
    }

    public ProtocolBuffer(int packetId) {
        this.packetId = packetId;
        this.internal = new byte[1024 * 8];
    }

    public ProtocolBuffer(int packetId, final int capacity) {
        this.packetId = packetId;
        this.internal = new byte[capacity];
    }

    // write read

    public void writeByte(final byte value) {
        this.checkCapacity(Action.WRITE, 1);

        this.internal[writePosition++] = value;
    }

    public byte readByte() {
        this.checkCapacity(Action.READ, 1);
        return internal[readPosition++];
    }

    // should not use such weird ints xd
    @Deprecated @SuppressWarnings("all")
    public void write127Int(final int value) {
        if (value > 127 || value < -127)
            throw new IllegalStateException("int is to big or small");

        this.writeByte((byte) value);
    }

    // should not use such weird ints xd
    @Deprecated @SuppressWarnings("all")
    public int read127Int() {
        return this.readByte();
    }

    public void writeVarInt(int value) {
        while ((value & 0xFFFFFF80) != 0x0) {
            this.write127Int((value & 0x7F) | 0x80);
            value >>>= 7;
        }
        this.write127Int(value);
    }

    public int readVarInt() {
        int value = 0;
        int index = 0;
        byte current;
        do {
            current = this.readByte();
            value |= (current & 0x7F) << index++ * 7;
        } while ((current & 0x80) == 0x80);
        return value;
    }

    public void writeByteArray(final byte... bytes) {
        for (byte aByte : bytes)
            this.writeByte(aByte);
    }

    public byte[] readByteArray(final byte[] output) {
        for (int i = 0; i < output.length; i++)
            output[i] = this.readByte();

        return output;
    }

    public void writeString(final String value) {
        byte[] dataArray = value.getBytes(StandardCharsets.UTF_8);
        final int length = dataArray.length;
        this.writeVarInt(length);
        this.writeByteArray(dataArray);
    }

    public String readString() {
        final int length = this.readVarInt();
        final byte[] dataArray = new byte[length];
        this.readByteArray(dataArray);
        return new String(dataArray, StandardCharsets.UTF_8);
    }

    // units

    public void unfold() {
        final byte[] copy = new byte[this.writePosition];
        System.arraycopy(this.internal, 0, copy, 0, this.writePosition);
        this.internal = copy;
    }

    public int length() {
        return this.internal.length;
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

    public String dump() {
        return "PacketDump: \n" + "  Id: " + this.packetId + "\n Bytes:" + Arrays.toString(this.internal);
    }

    public enum Action {
        READ, WRITE
    }

    public ProtocolBuffer clone() {
        return new ProtocolBuffer(this.packetId, this.internal);
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

    public int getPacketId() {
        return packetId;
    }

}
