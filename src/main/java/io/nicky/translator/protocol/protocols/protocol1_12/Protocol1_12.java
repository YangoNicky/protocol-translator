package io.nicky.translator.protocol.protocols.protocol1_12;

import io.nicky.translator.protocol.protocol.AbstractProtocol;
import io.nicky.translator.protocol.protocol.ProtocolId;
import io.nicky.translator.protocol.protocol.ProtocolVersion;

@ProtocolId(
        id = 20,
        from = ProtocolVersion.V_1_12_1,
        current = ProtocolVersion.V_1_12,
        to = ProtocolVersion.V_1_11_2
)
public final class Protocol1_12 extends AbstractProtocol {

    @Override
    public void registerVersionUp() {

    }

    @Override
    public void registerVersionDown() {

    }
}
