package io.nicky.translator.protocol.protocols.protocol1_14_3;

import io.nicky.translator.protocol.protocol.AbstractProtocol;
import io.nicky.translator.protocol.protocol.ProtocolId;
import io.nicky.translator.protocol.protocol.ProtocolVersion;

@ProtocolId(
        id = 11,
        from = ProtocolVersion.V_1_14_4,
        current = ProtocolVersion.V_1_14_3,
        to = ProtocolVersion.V_1_14_2
)
public final class Protocol1_14_3 extends AbstractProtocol {

    @Override
    public void registerVersionUp() {

    }

    @Override
    public void registerVersionDown() {

    }
}
