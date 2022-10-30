package io.nicky.translator.protocol.protocols.protocol1_16_5;

import io.nicky.translator.protocol.protocol.AbstractProtocol;
import io.nicky.translator.protocol.protocol.ProtocolId;
import io.nicky.translator.protocol.protocol.ProtocolVersion;

@ProtocolId(
        id = 5,
        from = ProtocolVersion.V_1_17,
        current = {
                ProtocolVersion.V_1_16, ProtocolVersion.V_1_16_5
        },
        to = ProtocolVersion.V_1_15
)
public final class Protocol1_16_5 extends AbstractProtocol {

    @Override
    public void registerDownTransformation() {

    }


}
