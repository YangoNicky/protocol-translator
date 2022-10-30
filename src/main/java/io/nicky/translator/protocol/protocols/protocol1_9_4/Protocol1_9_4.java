package io.nicky.translator.protocol.protocols.protocol1_9_4;

import io.nicky.translator.protocol.protocol.AbstractProtocol;
import io.nicky.translator.protocol.protocol.ProtocolId;
import io.nicky.translator.protocol.protocol.ProtocolVersion;

@ProtocolId(
        id = 24,
        from = ProtocolVersion.V_1_10,
        current = {
                ProtocolVersion.V_1_9_1,
                ProtocolVersion.V_1_9_4
        },
        to = ProtocolVersion.V_1_8_8
)
public final class Protocol1_9_4 extends AbstractProtocol {

    @Override
    public void registerDownTransformation() {

    }


}
