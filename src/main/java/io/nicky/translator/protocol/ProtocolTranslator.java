package io.nicky.translator.protocol;

import io.nicky.translator.protocol.protocol.ProtocolManager;
import io.nicky.translator.protocol.protocol.ProtocolVersion;
import io.nicky.translator.protocol.transformation.TransformerManager;
import io.nicky.translator.protocol.units.Pair;

public abstract class ProtocolTranslator {

    public abstract void initialize();

    public abstract Pair<ProtocolVersion, ProtocolVersion> range();

    public abstract void shutdown();

    public abstract ProtocolManager getProtocolManager();

    public abstract TransformerManager getTransformerManager();
}
