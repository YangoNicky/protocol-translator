package io.nicky.translator.protocol.protocols.protocol1_15;

import io.nicky.translator.protocol.protocol.AbstractProtocol;
import io.nicky.translator.protocol.protocol.ProtocolId;
import io.nicky.translator.protocol.protocol.ProtocolVersion;

@ProtocolId(
        id = 9,
        from = ProtocolVersion.V_1_15_1,
        current = ProtocolVersion.V_1_15,
        to = ProtocolVersion.V_1_14_4
)
public final class Protocol1_15 extends AbstractProtocol {

    @Override
    public void registerDownTransformation() {

    }


}
