package io.nicky.translator.protocol.transformation;

import io.nicky.translator.protocol.ProtocolTranslator;
import io.nicky.translator.protocol.data.ProtocolBuffer;
import io.nicky.translator.protocol.protocol.AbstractProtocol;
import io.nicky.translator.protocol.protocol.Direction;
import io.nicky.translator.protocol.protocol.ProtocolVersion;
import io.nicky.translator.protocol.protocol.packet.PacketRewriter;
import io.nicky.translator.protocol.units.PacketCallback;
import io.nicky.translator.protocol.user.ProtocolUser;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public final class TransformerManager {

    private static final Map<ProtocolUser, AbstractProtocol[]> predefinedProtocols = new HashMap<>();

    private static long currentLoaderId;
    private static final ExecutorService executor = Executors.newCachedThreadPool(runnable -> {
        final Thread thread = new Thread(runnable, "loader-" + currentLoaderId++);
        if (runnable instanceof TransformationLoader loader) {
            loader.preExecution();
            thread.setPriority(Thread.MAX_PRIORITY);
            return thread;
        }
        thread.setDaemon(true);
        thread.setPriority(Thread.MAX_PRIORITY);
        return thread;
    });

    private final ProtocolTranslator translator;

    public TransformerManager(ProtocolTranslator translator) {
        this.translator = translator;
    }

    public void disconnectPlayer(final ProtocolUser protocolUser) {
        predefinedProtocols.remove(protocolUser);
    }

    public ProtocolBuffer translate(final Direction.PacketDirection direction, final ProtocolUser user, ProtocolBuffer protocolBuffer) {
        final int packetId = protocolBuffer.getPacketId();
        final ProtocolVersion baseVersion = translator.range().getLast();
        final ProtocolVersion playerVersion = user.getVersion();

        if (playerVersion.isHigher(baseVersion))
            throw new IllegalStateException("protocol version is out of range; " + playerVersion.name() + " > " + baseVersion.name());

        final AbstractProtocol[] protocols = predefinedProtocols
                .getOrDefault(user, this.rangeFor(playerVersion, translator.range().getFirst()));

        if (!predefinedProtocols.containsKey(user))
            predefinedProtocols.put(user, protocols);

        ProtocolBuffer buffer = protocolBuffer.clone();
        for (AbstractProtocol protocol : protocols) {
            for (PacketRewriter packetRewriter : protocol.getPacketTranslators().stream()
                    .filter(translator -> translator.getPacketId() == packetId)
                    .filter(translator -> translator.getDirection() == direction)
                    .map(AbstractProtocol.PacketTranslator::getPacketRewriter).toList()) {

                final ProtocolBuffer transformed = packetRewriter
                        .transformPacket(buffer, new ProtocolBuffer(buffer.length() * 2));

                transformed.unfold();
                buffer = transformed;
            }
        }

        return buffer;
    }

    public AbstractProtocol[] rangeFor(final ProtocolVersion from, final ProtocolVersion to) {
        final int startOrdinal = from.ordinal(), lastOrdinal = to.ordinal();

        if (startOrdinal > lastOrdinal)
            throw new IllegalStateException("Unsupported direction for range: " + from.name() + " -> " + to.name());

        // used to filter sub version 1.14.1, 1.14.2....
        final List<AbstractProtocol> skipAble = new ArrayList<>();
        final List<AbstractProtocol> protocols = new ArrayList<>();

        for (int index = startOrdinal; index < lastOrdinal + 1; index++) {
            ProtocolVersion version = null;
            for (ProtocolVersion value : ProtocolVersion.values()) {
                if (value.ordinal() == index)
                    version = value;
            }

            if (version == null)
                throw new IllegalStateException("Protocol with invalid ordinal found!: " + index);

            final AbstractProtocol protocol = translator
                    .getProtocolManager().getProtocolForVersion(version);

            if (skipAble.contains(protocol))
                continue;

            skipAble.add(protocol);
            protocols.add(translator.getProtocolManager().getProtocolForVersion(version));
        }

        final AbstractProtocol[] protocolArray = new AbstractProtocol[protocols.size()];
        for (int current = 0; current < protocolArray.length; current++) {
            protocolArray[current] = protocols.get(current);
        }

        return protocolArray;
    }

    public void shutdown() {
        if (executor.isTerminated())
            return;

        executor.shutdown();
    }
}
