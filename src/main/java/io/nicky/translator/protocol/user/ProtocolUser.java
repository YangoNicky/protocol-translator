package io.nicky.translator.protocol.user;

import io.nicky.translator.protocol.protocol.ProtocolVersion;

public final class ProtocolUser {

    private ProtocolVersion version;

    public void setVersion(ProtocolVersion version) {
        this.version = version;
    }

    public ProtocolVersion getVersion() {
        return version;
    }
}
