package io.nicky.translator.protocol.protocols.protocol1_7_10;

import io.nicky.translator.protocol.protocol.AbstractProtocol;
import io.nicky.translator.protocol.protocol.ProtocolId;
import io.nicky.translator.protocol.protocol.ProtocolVersion;

@ProtocolId(
        id = 27,
        from = ProtocolVersion.V_1_8_8,
        current = ProtocolVersion.V_1_7_10,
        to = ProtocolVersion.NONE
)
public final class Protocol1_7_10 extends AbstractProtocol {

    @Override
    public void registerDownTransformation() {

    }


}
