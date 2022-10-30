package io.nicky.translator.protocol.protocol;

import io.nicky.translator.protocol.protocols.dummy.ProtocolDummy;
import io.nicky.translator.protocol.protocols.protocol1_10_2.Protocol1_10_2;
import io.nicky.translator.protocol.protocols.protocol1_11_2.Protocol1_11_2;
import io.nicky.translator.protocol.protocols.protocol1_12_2.Protocol1_12_2;
import io.nicky.translator.protocol.protocols.protocol1_13_2.Protocol1_13_2;
import io.nicky.translator.protocol.protocols.protocol1_14_4.Protocol1_14_4;
import io.nicky.translator.protocol.protocols.protocol1_15_2.Protocol1_15_2;
import io.nicky.translator.protocol.protocols.protocol1_16_5.Protocol1_16_5;
import io.nicky.translator.protocol.protocols.protocol1_17_1.Protocol1_17_1;
import io.nicky.translator.protocol.protocols.protocol1_18.Protocol1_18;
import io.nicky.translator.protocol.protocols.protocol1_19_1.Protocol1_19_1;
import io.nicky.translator.protocol.protocols.protocol1_7_10.Protocol1_7_10;
import io.nicky.translator.protocol.protocols.protocol1_8_9.Protocol1_8_9;
import io.nicky.translator.protocol.protocols.protocol1_9_4.Protocol1_9_4;
import io.nicky.translator.protocol.units.DebugLogger;
import io.nicky.translator.protocol.units.StringUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class ProtocolManager {

    private final DebugLogger logger = DebugLogger.INTERNAL;

    private final Map<ProtocolId, AbstractProtocol> protocols = new ConcurrentHashMap<>();

    public void initialize() {
        logger.debug("Registering protocols: ");
        registerProtocol(new ProtocolDummy());

        registerProtocol(new Protocol1_19_1());
        registerProtocol(new Protocol1_18());
        registerProtocol(new Protocol1_17_1());
        registerProtocol(new Protocol1_16_5());
        registerProtocol(new Protocol1_15_2());
        registerProtocol(new Protocol1_14_4());
        registerProtocol(new Protocol1_13_2());
        registerProtocol(new Protocol1_12_2());
        registerProtocol(new Protocol1_11_2());
        registerProtocol(new Protocol1_10_2());
        registerProtocol(new Protocol1_9_4());
        registerProtocol(new Protocol1_8_9());
        registerProtocol(new Protocol1_7_10());

        logger.debug("Successfully loaded %s protocols!", this.protocols.size());
    }

    public void registerProtocol(final AbstractProtocol protocol) {
        protocol.initialize(this);

        // used for utility that needs to stay in load order!
        protocol.registerOther();

        protocol.registerDownTransformation();
    }

    public boolean validate(final AbstractProtocol protocol, final ProtocolId protocolId) {
        if (protocols.entrySet().stream().noneMatch(entry -> entry.getKey().id() == protocolId.id()
                || entry.getKey().current() == protocolId.current())) {

            String spacing = "".repeat(9 - protocolId.current()[0].name().length());

            String subElement = protocolId.current().length > 1 ?
                    spacing + "(%s)".formatted(protocolId.current().length - 1) : "";

            logger.debug(" -> %s %s", protocolId.current()[0], subElement);

            this.protocols.put(protocolId, protocol);
        }
        return false;
    }

    public void shutdown() {
        this.protocols.forEach((protocolId, protocol) -> protocol.shutdown());
        this.protocols.clear();
    }

}
