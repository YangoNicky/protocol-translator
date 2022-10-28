package io.nicky.translator.protocol.protocols.protocol1_13_1;

import io.nicky.translator.protocol.protocol.AbstractProtocol;
import io.nicky.translator.protocol.protocol.ProtocolId;
import io.nicky.translator.protocol.protocol.ProtocolVersion;

@ProtocolId(
        id = 16,
        from = ProtocolVersion.V_1_13_2,
        current = ProtocolVersion.V_1_13_1,
        to = ProtocolVersion.V_1_13
)
public final class Protocol1_13_1 extends AbstractProtocol {

    @Override
    public void registerVersionUp() {

    }

    @Override
    public void registerVersionDown() {

    }
}
