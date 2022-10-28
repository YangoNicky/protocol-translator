package io.nicky.translator.protocol.protocols.protocol1_8_9;

import io.nicky.translator.protocol.protocol.AbstractProtocol;
import io.nicky.translator.protocol.protocol.ProtocolId;
import io.nicky.translator.protocol.protocol.ProtocolVersion;

@ProtocolId(
        id = 26,
        from = ProtocolVersion.V_1_9_1,
        current = ProtocolVersion.V_1_8_8_R1,
        to = ProtocolVersion.V_1_7_10
)
public final class Protocol1_8_9 extends AbstractProtocol {

    @Override
    public void registerVersionUp() {

    }

    @Override
    public void registerVersionDown() {

    }
}
