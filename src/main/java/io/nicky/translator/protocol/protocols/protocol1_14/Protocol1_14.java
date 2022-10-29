package io.nicky.translator.protocol.protocols.protocol1_14;

import io.nicky.translator.protocol.protocol.AbstractProtocol;
import io.nicky.translator.protocol.protocol.ProtocolId;
import io.nicky.translator.protocol.protocol.ProtocolVersion;

@ProtocolId(
        id = 14,
        from = ProtocolVersion.V_1_14_1,
        current = ProtocolVersion.V_1_14,
        to = ProtocolVersion.V_1_13_2
)
public final class Protocol1_14 extends AbstractProtocol {

    @Override
    public void registerDownTransformation() {

    }


}
