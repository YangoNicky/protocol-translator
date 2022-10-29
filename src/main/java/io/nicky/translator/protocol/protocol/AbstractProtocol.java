package io.nicky.translator.protocol.protocol;

import io.nicky.translator.protocol.protocol.json.JsonRewriter;
import io.nicky.translator.protocol.protocol.packet.PacketRewriter;

public abstract class AbstractProtocol {

    public abstract void registerDownTransformation();

    public AbstractProtocol initialize(final ProtocolManager protocolManager) {
        final ProtocolId protocolId = this.getClass().getAnnotation(ProtocolId.class);

        if (protocolId == null)
            throw new IllegalStateException("Protocol is missing @ProtocolId annotation... (" + this.getClass().getSimpleName() + ")");

        if (protocolManager.validate(this, protocolId)) {
            // todo run finalizing code
        }

        return this;
    }

    public void registerPacketTransformer(final int packetId, final int cap, PacketRewriter packetRewriter) {

    }
    public void registerPacketTransformer(final int packetId, PacketRewriter packetRewriter) {

    }

    public void registerJsonRewriter(final JsonRewriter jsonRewriter) {

    }

    public void registerOther() {
    }

    public void shutdown() {
    }

}
