package io.nicky.translator.protocol.protocols.protocol1_11_2;

import io.nicky.translator.protocol.protocol.AbstractProtocol;
import io.nicky.translator.protocol.protocol.ProtocolId;
import io.nicky.translator.protocol.protocol.ProtocolVersion;

@ProtocolId(
        id = 21,
        from = ProtocolVersion.V_1_12,
        current = ProtocolVersion.V_1_11_2,
        to = ProtocolVersion.V_1_11
)
public final class Protocol1_11_2 extends AbstractProtocol {

    @Override
    public void registerVersionUp() {

    }

    @Override
    public void registerVersionDown() {

    }
}
