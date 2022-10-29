package io.nicky.translator.protocol.protocols.protocol1_14_4;

import io.nicky.translator.protocol.protocol.AbstractProtocol;
import io.nicky.translator.protocol.protocol.ProtocolId;
import io.nicky.translator.protocol.protocol.ProtocolVersion;

@ProtocolId(
        id = 10,
        from = ProtocolVersion.V_1_15,
        current = ProtocolVersion.V_1_14_4,
        to = ProtocolVersion.V_1_14_3
)
public final class Protocol1_14_4 extends AbstractProtocol {

    @Override
    public void registerDownTransformation() {

    }


}
