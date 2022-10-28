package io.nicky.translator.protocol.protocols.protocol1_12_1;

import io.nicky.translator.protocol.protocol.AbstractProtocol;
import io.nicky.translator.protocol.protocol.ProtocolId;
import io.nicky.translator.protocol.protocol.ProtocolVersion;

@ProtocolId(
        id = 19,
        from = ProtocolVersion.V_1_12_2,
        current = ProtocolVersion.V_1_12_1,
        to = ProtocolVersion.V_1_12
)
public final class Protocol1_12_1 extends AbstractProtocol {

    @Override
    public void registerVersionUp() {

    }

    @Override
    public void registerVersionDown() {

    }
}
