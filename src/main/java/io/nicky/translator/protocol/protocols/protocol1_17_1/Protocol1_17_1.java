package io.nicky.translator.protocol.protocols.protocol1_17_1;

import io.nicky.translator.protocol.protocol.AbstractProtocol;
import io.nicky.translator.protocol.protocol.ProtocolId;
import io.nicky.translator.protocol.protocol.ProtocolVersion;

@ProtocolId(
        id = 3,
        from = ProtocolVersion.V_1_18,
        current = ProtocolVersion.V_1_17_1,
        to = ProtocolVersion.V_1_17
)
public final class Protocol1_17_1 extends AbstractProtocol {

    @Override
    public void registerVersionUp() {

    }

    @Override
    public void registerVersionDown() {

    }
}
