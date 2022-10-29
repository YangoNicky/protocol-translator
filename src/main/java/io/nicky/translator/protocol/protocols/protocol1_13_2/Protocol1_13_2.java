package io.nicky.translator.protocol.protocols.protocol1_13_2;

import io.nicky.translator.protocol.protocol.AbstractProtocol;
import io.nicky.translator.protocol.protocol.ProtocolId;
import io.nicky.translator.protocol.protocol.ProtocolVersion;

@ProtocolId(
        id = 15,
        from = ProtocolVersion.V_1_14,
        current = ProtocolVersion.V_1_13_2,
        to = ProtocolVersion.V_1_13_1
)
public final class Protocol1_13_2 extends AbstractProtocol {

    @Override
    public void registerDownTransformation() {

    }


}
