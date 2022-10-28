package io.nicky.translator.protocol.protocol;

public abstract class AbstractProtocol {

    public abstract void registerVersionUp();

    public abstract void registerVersionDown();

    public AbstractProtocol initialize(final ProtocolManager protocolManager) {
        final ProtocolId protocolId = this.getClass().getAnnotation(ProtocolId.class);

        if (protocolId == null)
            throw new IllegalStateException("Protocol is missing @ProtocolId annotation... (" + this.getClass().getSimpleName() + ")");

        if (protocolManager.validate(this, protocolId)) {
            // todo run finalizing code
        }

        return this;
    }

    public void registerOther() {
    }

    public void shutdown() {
    }

}
