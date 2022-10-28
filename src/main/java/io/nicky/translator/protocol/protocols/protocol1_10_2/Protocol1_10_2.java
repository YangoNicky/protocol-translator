package io.nicky.translator.protocol.protocols.protocol1_10_2;

import io.nicky.translator.protocol.protocol.AbstractProtocol;
import io.nicky.translator.protocol.protocol.ProtocolId;
import io.nicky.translator.protocol.protocol.ProtocolVersion;

@ProtocolId(
        id = 23,
        from = ProtocolVersion.V_1_11,
        current = ProtocolVersion.V_1_10_2,
        to = ProtocolVersion.V_1_9_4
)
public final class Protocol1_10_2 extends AbstractProtocol {

    @Override
    public void registerVersionUp() {

    }

    @Override
    public void registerVersionDown() {

    }
}
