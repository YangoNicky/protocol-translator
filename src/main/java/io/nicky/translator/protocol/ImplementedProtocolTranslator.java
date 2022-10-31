package io.nicky.translator.protocol;

import io.nicky.translator.protocol.protocol.ProtocolManager;
import io.nicky.translator.protocol.protocol.ProtocolVersion;
import io.nicky.translator.protocol.transformation.TransformerManager;
import io.nicky.translator.protocol.units.Pair;

@SuppressWarnings("unused")
public final class ImplementedProtocolTranslator extends ProtocolTranslator {

    private final ProtocolManager protocolManager;
    private final TransformerManager transformerManager;
    private final Thread mainThread;

    private final ProtocolVersion from;
    private final ProtocolVersion to;

    public ImplementedProtocolTranslator() {
        this(ProtocolVersion.oldest(), ProtocolVersion.latest());
    }

    public ImplementedProtocolTranslator(final ProtocolVersion base) {
        this(base, ProtocolVersion.latest());
    }

    public ImplementedProtocolTranslator(ProtocolVersion from, ProtocolVersion to) {
        this.from = from;
        this.to = to;

        this.mainThread = Thread.currentThread();

        this.protocolManager = new ProtocolManager();
        this.transformerManager = new TransformerManager(this);
    }

    @Override
    public void initialize() {
        mainThread.setName("protocol-main");

        this.protocolManager.initialize();

        Runtime.getRuntime().addShutdownHook(
                new Thread(this::shutdown, "shutdown-hook"));
    }

    @Override
    public Pair<ProtocolVersion, ProtocolVersion> range() {
        return new Pair<>(this.from, this.to);
    }

    @Override
    public void shutdown() {
        this.transformerManager.shutdown();
        this.protocolManager.shutdown();
    }

    @Override
    public ProtocolManager getProtocolManager() {
        return protocolManager;
    }

    @Override
    public TransformerManager getTransformerManager() {
        return transformerManager;
    }
}
