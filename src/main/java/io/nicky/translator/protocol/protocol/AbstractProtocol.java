package io.nicky.translator.protocol.protocol;

import io.nicky.translator.protocol.protocol.json.JsonRewriter;
import io.nicky.translator.protocol.protocol.packet.PacketRewriter;

/**
 * The type Abstract protocol.
 */
public abstract class AbstractProtocol {

    /**
     * Register down transformation.
     */
    public abstract void registerDownTransformation();

    /**
     * Initialize abstract protocol.
     *
     * @param protocolManager the protocol manager
     * @return the abstract protocol
     */
    public AbstractProtocol initialize(final ProtocolManager protocolManager) {
        final ProtocolId protocolId = this.getClass().getAnnotation(ProtocolId.class);

        if (protocolId == null)
            throw new IllegalStateException("Protocol is missing @ProtocolId annotation... (" + this.getClass().getSimpleName() + ")");

        if (protocolManager.validate(this, protocolId)) {
            // todo run finalizing code
        }

        return this;
    }

    /**
     * Register packet transformer for packets that gets sent to the client.
     *
     * @param packetId       the packet id
     * @param cap            the cap
     * @param packetRewriter the packet rewriter
     */
    @Direction(Direction.PacketDirection.TO_CLIENT)
    public void registerPacketTransformerToClient(final int packetId, final int cap, PacketRewriter packetRewriter) {

    }

    /**
     * Register packet transformer for packets that gets sent to the client.
     *
     * @param packetId       the packet id
     * @param packetRewriter the packet rewriter
     */
    @Direction(Direction.PacketDirection.TO_CLIENT)
    public void registerPacketTransformerToClient(final int packetId, PacketRewriter packetRewriter) {
    }

    /**
     * Register packet transformer for packets that gets sent to the server.
     *
     * @param packetId       the packet id
     * @param cap            the cap
     * @param packetRewriter the packet rewriter
     */
    @Direction(Direction.PacketDirection.TO_SERVER)
    public void registerPacketTransformerToServer(final int packetId, final int cap, PacketRewriter packetRewriter) {

    }

    /**
     * Register packet transformer for packets that gets sent to the server.
     *
     * @param packetId       the packet id
     * @param packetRewriter the packet rewriter
     */
    @Direction(Direction.PacketDirection.TO_SERVER)
    public void registerPacketTransformerToServer(final int packetId, PacketRewriter packetRewriter) {

    }

    /**
     * register JsonRewriter for ChatComponents, ...
     *
     * @param jsonRewriter the json rewriter
     */
    public void registerJsonRewriter(final JsonRewriter jsonRewriter) {

    }

    /**
     * Register other utility stuff for internal protocol.
     */
    public void registerOther() {
    }

    /**
     * Shutdown.
     */
    public void shutdown() {
    }

}
