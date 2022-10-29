package io.nicky.translator.protocol.protocols.protocol1_11;

import io.nicky.translator.protocol.protocol.AbstractProtocol;
import io.nicky.translator.protocol.protocol.ProtocolId;
import io.nicky.translator.protocol.protocol.ProtocolVersion;

@ProtocolId(
        id = 22,
        from = ProtocolVersion.V_1_11_2,
        current = ProtocolVersion.V_1_11,
        to = ProtocolVersion.V_1_10_2
)
public final class Protocol1_11 extends AbstractProtocol {

    @Override
    public void registerDownTransformation() {

    }


}
