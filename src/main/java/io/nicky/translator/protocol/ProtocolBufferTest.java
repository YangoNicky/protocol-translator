package io.nicky.translator.protocol;

import io.nicky.translator.protocol.data.ProtocolBuffer;

public final class ProtocolBufferTest {

    public static void main(String[] args) {
        final ProtocolBuffer protocolBuffer = new ProtocolBuffer(2);

        protocolBuffer.writeByte((byte) 1);
        protocolBuffer.writeByte((byte) 2);

        System.out.println("first: " + protocolBuffer.readByte());
        System.out.println("last: " + protocolBuffer.readByte());
        // should not happen gets caught!
        System.out.println("over limit: " + protocolBuffer.readByte());
    }

}
