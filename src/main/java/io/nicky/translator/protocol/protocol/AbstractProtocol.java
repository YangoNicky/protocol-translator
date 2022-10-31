package io.nicky.translator.protocol.protocol;

import io.nicky.translator.protocol.protocol.json.JsonRewriter;
import io.nicky.translator.protocol.protocol.packet.PacketRewriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type Abstract protocol.
 */
public abstract class AbstractProtocol {

    private final List<PacketTranslator> packetTranslators = new ArrayList<>();

    private ProtocolId internalId;
    private ProtocolManager protocolManager;

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
    public AbstractProtocol initialize(final ProtocolId assigned, final ProtocolManager protocolManager) {
        this.internalId = assigned;
        this.protocolManager = protocolManager;
        return this;
    }

    /**
     * Register packet transformer for packets that gets sent to the client.
     *
     * @param packetId       the packet id
     * @param packetRewriter the packet rewriter
     */
    @Direction(Direction.PacketDirection.TO_CLIENT)
    public void registerPacketTransformerToClient(final int packetId, PacketRewriter packetRewriter) {
        registerPacketTransformer0(Direction.PacketDirection.TO_CLIENT, packetId, packetRewriter);
    }

    /**
     * Register packet transformer for packets that gets sent to the server.
     *
     * @param packetId       the packet id
     * @param packetRewriter the packet rewriter
     */
    @Direction(Direction.PacketDirection.TO_SERVER)
    public void registerPacketTransformerToServer(final int packetId, PacketRewriter packetRewriter) {
        registerPacketTransformer0(Direction.PacketDirection.TO_SERVER, packetId, packetRewriter);
    }

    private void registerPacketTransformer0(final Direction.PacketDirection direction, final int packetId, final PacketRewriter rewriter) {
        final List<Integer> packetIds = this.packetTranslators.stream().filter(packetTranslator -> packetTranslator.direction == direction)
                .map(packetTranslator -> packetTranslator.packetId).toList();

        if (packetIds.contains(packetId))
            throw new IllegalStateException("protocol already has PacketRewriter for packet id: " + packetId);

        final PacketTranslator translator = new PacketTranslator(direction, packetId, rewriter);
        this.packetTranslators.add(translator);
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

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + Arrays.toString(this.internalId.current());
    }

    public List<PacketTranslator> getPacketTranslators() {
        return packetTranslators;
    }

    public static class PacketTranslator {

        private final Direction.PacketDirection direction;
        private int packetId;
        private final PacketRewriter packetRewriter;

        public PacketTranslator(Direction.PacketDirection direction, int packetId, PacketRewriter packetRewriter) {
            this.direction = direction;
            this.packetId = packetId;
            this.packetRewriter = packetRewriter;
        }

        public Direction.PacketDirection getDirection() {
            return direction;
        }

        public void setPacketId(int packetId) {
            this.packetId = packetId;
        }

        public int getPacketId() {
            return packetId;
        }

        public PacketRewriter getPacketRewriter() {
            return packetRewriter;
        }
    }
}
