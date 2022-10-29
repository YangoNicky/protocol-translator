package io.nicky.translator.protocol.protocols.dummy;

import com.google.gson.JsonArray;
import io.nicky.translator.protocol.protocol.AbstractProtocol;
import io.nicky.translator.protocol.protocol.ProtocolId;
import io.nicky.translator.protocol.protocol.ProtocolVersion;

@ProtocolId(
        id = -1,
        from = ProtocolVersion.NONE,
        current = ProtocolVersion.NONE,
        to = ProtocolVersion.NONE
)
public final class ProtocolDummy extends AbstractProtocol {

    @Override
    public void registerDownTransformation() {

        this.registerJsonRewriter(upperVersion -> {
            upperVersion.setFormatter(text -> {


                return new JsonArray();
            });

        });

        this.registerPacketTransformer(0x01, 50, (from, to) -> {

        });

    }
}
