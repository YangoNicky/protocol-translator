package io.nicky.translator.protocol.protocols.protocol1_16;

import io.nicky.translator.protocol.protocol.AbstractProtocol;
import io.nicky.translator.protocol.protocol.ProtocolId;
import io.nicky.translator.protocol.protocol.ProtocolVersion;

@ProtocolId(
        id = 6,
        from = ProtocolVersion.V_1_16_5,
        current = ProtocolVersion.V_1_16,
        to = ProtocolVersion.V_1_15_2
)
public final class Protocol1_16 extends AbstractProtocol {

    @Override
    public void registerVersionUp() {

    }

    @Override
    public void registerVersionDown() {

    }
}
