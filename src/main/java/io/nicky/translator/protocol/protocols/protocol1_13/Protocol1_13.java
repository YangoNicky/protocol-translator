package io.nicky.translator.protocol.protocols.protocol1_13;

import io.nicky.translator.protocol.protocol.AbstractProtocol;
import io.nicky.translator.protocol.protocol.ProtocolId;
import io.nicky.translator.protocol.protocol.ProtocolVersion;

@ProtocolId(
        id = 17,
        from = ProtocolVersion.V_1_13_1,
        current = ProtocolVersion.V_1_13,
        to = ProtocolVersion.V_1_12_2
)
public final class Protocol1_13 extends AbstractProtocol {

    @Override
    public void registerDownTransformation() {

    }


}
