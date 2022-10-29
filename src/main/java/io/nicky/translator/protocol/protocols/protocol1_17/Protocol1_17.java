package io.nicky.translator.protocol.protocols.protocol1_17;

import io.nicky.translator.protocol.protocol.AbstractProtocol;
import io.nicky.translator.protocol.protocol.ProtocolId;
import io.nicky.translator.protocol.protocol.ProtocolVersion;

@ProtocolId(
        id = 4,
        from = ProtocolVersion.V_1_17_1,
        current = ProtocolVersion.V_1_17,
        to = ProtocolVersion.V_1_16_5
)
public final class Protocol1_17 extends AbstractProtocol {

    @Override
    public void registerDownTransformation() {

    }


}
