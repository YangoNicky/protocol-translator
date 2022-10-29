package io.nicky.translator.protocol.protocols.protocol1_18;

import io.nicky.translator.protocol.protocol.AbstractProtocol;
import io.nicky.translator.protocol.protocol.ProtocolId;
import io.nicky.translator.protocol.protocol.ProtocolVersion;

@ProtocolId(
        id = 2,
        from = ProtocolVersion.V_1_19,
        current = ProtocolVersion.V_1_18,
        to = ProtocolVersion.V_1_17_1
)
public final class Protocol1_18 extends AbstractProtocol {

    @Override
    public void registerDownTransformation() {

    }


}
