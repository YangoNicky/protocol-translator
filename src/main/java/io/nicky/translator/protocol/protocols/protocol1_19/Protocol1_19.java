package io.nicky.translator.protocol.protocols.protocol1_19;

import io.nicky.translator.protocol.protocol.AbstractProtocol;
import io.nicky.translator.protocol.protocol.ProtocolId;
import io.nicky.translator.protocol.protocol.ProtocolVersion;

@ProtocolId(
        id = 1,
        from = ProtocolVersion.V_1_19_2,
        current = ProtocolVersion.V_1_19,
        to = ProtocolVersion.V_1_18
)
public final class Protocol1_19 extends AbstractProtocol {

    @Override
    public void registerVersionUp() {

    }

    @Override
    public void registerVersionDown() {

    }
}
