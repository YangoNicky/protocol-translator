package io.nicky.translator.protocol.protocol;

import io.nicky.translator.protocol.protocols.dummy.ProtocolDummy;
import io.nicky.translator.protocol.protocols.protocol1_10_2.Protocol1_10_2;
import io.nicky.translator.protocol.protocols.protocol1_11.Protocol1_11;
import io.nicky.translator.protocol.protocols.protocol1_11_2.Protocol1_11_2;
import io.nicky.translator.protocol.protocols.protocol1_12.Protocol1_12;
import io.nicky.translator.protocol.protocols.protocol1_12_1.Protocol1_12_1;
import io.nicky.translator.protocol.protocols.protocol1_12_2.Protocol1_12_2;
import io.nicky.translator.protocol.protocols.protocol1_13.Protocol1_13;
import io.nicky.translator.protocol.protocols.protocol1_13_1.Protocol1_13_1;
import io.nicky.translator.protocol.protocols.protocol1_13_2.Protocol1_13_2;
import io.nicky.translator.protocol.protocols.protocol1_14.Protocol1_14;
import io.nicky.translator.protocol.protocols.protocol1_14_1.Protocol1_14_1;
import io.nicky.translator.protocol.protocols.protocol1_14_2.Protocol1_14_2;
import io.nicky.translator.protocol.protocols.protocol1_14_3.Protocol1_14_3;
import io.nicky.translator.protocol.protocols.protocol1_14_4.Protocol1_14_4;
import io.nicky.translator.protocol.protocols.protocol1_15.Protocol1_15;
import io.nicky.translator.protocol.protocols.protocol1_15_1.Protocol1_15_1;
import io.nicky.translator.protocol.protocols.protocol1_15_2.Protocol1_15_2;
import io.nicky.translator.protocol.protocols.protocol1_16.Protocol1_16;
import io.nicky.translator.protocol.protocols.protocol1_16_5.Protocol1_16_5;
import io.nicky.translator.protocol.protocols.protocol1_17.Protocol1_17;
import io.nicky.translator.protocol.protocols.protocol1_17_1.Protocol1_17_1;
import io.nicky.translator.protocol.protocols.protocol1_18.Protocol1_18;
import io.nicky.translator.protocol.protocols.protocol1_19.Protocol1_19;
import io.nicky.translator.protocol.protocols.protocol1_19_1.Protocol1_19_1;
import io.nicky.translator.protocol.protocols.protocol1_7_10.Protocol1_7_10;
import io.nicky.translator.protocol.protocols.protocol1_8_9.Protocol1_8_9;
import io.nicky.translator.protocol.protocols.protocol1_9_1.Protocol1_9_1;
import io.nicky.translator.protocol.protocols.protocol1_9_4.Protocol1_9_4;
import io.nicky.translator.protocol.protocols.protocolnone.ProtocolNone;
import io.nicky.translator.protocol.units.DebugLogger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class ProtocolManager {

    private final DebugLogger logger = DebugLogger.INTERNAL;

    private final Map<ProtocolId, AbstractProtocol> protocols = new ConcurrentHashMap<>();

    public void initialize() {
        logger.debug("Registering protocols: ");
        registerProtocol(new ProtocolDummy());

        registerProtocol(new Protocol1_19_1());
        registerProtocol(new Protocol1_19());
        registerProtocol(new Protocol1_18());
        registerProtocol(new Protocol1_17_1());
        registerProtocol(new Protocol1_17());
        registerProtocol(new Protocol1_16_5());
        registerProtocol(new Protocol1_16());
        registerProtocol(new Protocol1_15_2());
        registerProtocol(new Protocol1_15_1());
        registerProtocol(new Protocol1_15());
        registerProtocol(new Protocol1_14_4());
        registerProtocol(new Protocol1_14_3());
        registerProtocol(new Protocol1_14_2());
        registerProtocol(new Protocol1_14_1());
        registerProtocol(new Protocol1_14());
        registerProtocol(new Protocol1_13_2());
        registerProtocol(new Protocol1_13_1());
        registerProtocol(new Protocol1_13());
        registerProtocol(new Protocol1_12_2());
        registerProtocol(new Protocol1_12_1());
        registerProtocol(new Protocol1_12());
        registerProtocol(new Protocol1_11_2());
        registerProtocol(new Protocol1_11());
        registerProtocol(new Protocol1_10_2());
        registerProtocol(new Protocol1_9_4());
        registerProtocol(new Protocol1_9_1());
        registerProtocol(new Protocol1_8_9());
        registerProtocol(new Protocol1_7_10());
        registerProtocol(new ProtocolNone());

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

            logger.debug(" -> %s", protocolId.current().getName());

            this.protocols.put(protocolId, protocol);
        }
        return false;
    }

    public void shutdown() {
        this.protocols.forEach((protocolId, protocol) -> protocol.shutdown());
        this.protocols.clear();
    }

}
