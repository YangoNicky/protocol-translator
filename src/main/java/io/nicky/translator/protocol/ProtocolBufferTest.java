package io.nicky.translator.protocol;

import io.nicky.translator.protocol.data.ProtocolBuffer;

import java.util.Arrays;

public final class ProtocolBufferTest {

    public static void main(String[] args) {
        final ProtocolBuffer protocolBuffer = new ProtocolBuffer(50);

        final byte[] toWrite = new byte[]{1, 3, 3, 7};
        final byte[] toRead = new byte[4];

        protocolBuffer.writeByte((byte) 1);
        protocolBuffer.writeByte((byte) 2);

        protocolBuffer.writeByteArray(toWrite);

        protocolBuffer.writeString("Hello, world!");

        System.out.println("first: " + protocolBuffer.readByte());
        System.out.println("last: " + protocolBuffer.readByte());

        System.out.println("array: " + Arrays.toString(protocolBuffer.readByteArray(toRead)));
        System.out.println("string: " + protocolBuffer.readString());

        // should not happen gets caught!
        System.out.println("over limit: " + protocolBuffer.readByte());
    }

}
