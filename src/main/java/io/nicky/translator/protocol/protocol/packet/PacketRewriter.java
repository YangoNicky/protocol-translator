package io.nicky.translator.protocol.protocol.packet;

import io.nicky.translator.protocol.data.ProtocolBuffer;

public interface PacketRewriter {

    void transformPacket(final ProtocolBuffer from, final ProtocolBuffer to);

}
