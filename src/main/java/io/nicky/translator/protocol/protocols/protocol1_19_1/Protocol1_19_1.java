package io.nicky.translator.protocol.protocols.protocol1_19_1;

import io.nicky.translator.protocol.protocol.AbstractProtocol;
import io.nicky.translator.protocol.protocol.ProtocolId;
import io.nicky.translator.protocol.protocol.ProtocolVersion;

@ProtocolId(
        id = 0,
        from = ProtocolVersion.NONE,
        current = {
                ProtocolVersion.V_1_19, ProtocolVersion.V_1_19_2
        },
        to = ProtocolVersion.V_1_18
)
public final class Protocol1_19_1 extends AbstractProtocol {

    @Override
    public void registerDownTransformation() {

    }


}
