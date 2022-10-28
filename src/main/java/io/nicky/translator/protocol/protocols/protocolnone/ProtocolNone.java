package io.nicky.translator.protocol.protocols.protocolnone;

import io.nicky.translator.protocol.protocol.AbstractProtocol;
import io.nicky.translator.protocol.protocol.ProtocolId;
import io.nicky.translator.protocol.protocol.ProtocolVersion;

@ProtocolId(
        id = 28,
        from = ProtocolVersion.V_1_7_10,
        current = ProtocolVersion.NONE,
        to = ProtocolVersion.NONE
)
public final class ProtocolNone extends AbstractProtocol {

    @Override
    public void registerVersionUp() {

    }

    @Override
    public void registerVersionDown() {

    }
}
