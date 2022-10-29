package io.nicky.translator.protocol.protocols.protocol1_14_1;

import io.nicky.translator.protocol.protocol.AbstractProtocol;
import io.nicky.translator.protocol.protocol.ProtocolId;
import io.nicky.translator.protocol.protocol.ProtocolVersion;

@ProtocolId(
        id = 13,
        from = ProtocolVersion.V_1_14_2,
        current = ProtocolVersion.V_1_14_1,
        to = ProtocolVersion.V_1_14
)
public final class Protocol1_14_1 extends AbstractProtocol {

    @Override
    public void registerDownTransformation() {

    }


}
