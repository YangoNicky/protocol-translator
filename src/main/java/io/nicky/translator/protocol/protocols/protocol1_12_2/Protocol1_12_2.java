package io.nicky.translator.protocol.protocols.protocol1_12_2;

import io.nicky.translator.protocol.protocol.AbstractProtocol;
import io.nicky.translator.protocol.protocol.ProtocolId;
import io.nicky.translator.protocol.protocol.ProtocolVersion;

@ProtocolId(
        id = 18,
        from = ProtocolVersion.V_1_13,
        current = {
                ProtocolVersion.V_1_12,
                ProtocolVersion.V_1_12_1,
                ProtocolVersion.V_1_12_2
        },
        to = ProtocolVersion.V_1_11
)
public final class Protocol1_12_2 extends AbstractProtocol {

    @Override
    public void registerDownTransformation() {

    }


}
