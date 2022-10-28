package io.nicky.translator.protocol;

import io.nicky.translator.protocol.protocol.ProtocolManager;

public final class ProtocolTranslator implements AutoCloseable {

    private final ProtocolManager protocolManager;
    private final Thread mainThread;

    public ProtocolTranslator() {
        this.mainThread = Thread.currentThread();

        this.protocolManager = new ProtocolManager();
    }

    public void initialize() {
        mainThread.setName("protocol-main");

        this.protocolManager.initialize();

        Runtime.getRuntime().addShutdownHook(
                new Thread(this::close, "shutdown-hook"));
    }

    @Override
    public void close() {
        this.protocolManager.shutdown();
    }

}
