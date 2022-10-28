package io.nicky.translator.protocol.buffer;

public final class ProtocolBuffer {

    final byte[] bufferData;

    public ProtocolBuffer(final int initialSize) {
        this.bufferData = new byte[initialSize];
    }
}
