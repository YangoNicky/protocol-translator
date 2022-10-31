package testing;

import io.nicky.translator.protocol.ImplementedProtocolTranslator;
import io.nicky.translator.protocol.data.ProtocolBuffer;
import io.nicky.translator.protocol.protocol.Direction;
import io.nicky.translator.protocol.protocol.ProtocolVersion;
import io.nicky.translator.protocol.user.ProtocolUser;

public class TestBootstrap {

    public static void main(String[] args) {
        final ImplementedProtocolTranslator translator = new ImplementedProtocolTranslator();
        translator.initialize();

        final ProtocolUser user = new ProtocolUser();
        user.setVersion(ProtocolVersion.V_1_17);

        final ProtocolBuffer buffer = new ProtocolBuffer(0x01);
        buffer.writeString("(hewwo ?)");

        // init with pre protocol sorting!
        {
            long transformTime = System.currentTimeMillis();
            System.out.println("result: " + translator.getTransformerManager()
                    .translate(Direction.PacketDirection.TO_CLIENT, user, buffer).readString());
            System.out.println("(first packet) 1. Took: " + (System.currentTimeMillis() - transformTime) + "ms");
        }
        // transformation with pre sorted protocols!
        {
            long transformTime = System.currentTimeMillis();
            translator.getTransformerManager()
                    .translate(Direction.PacketDirection.TO_CLIENT, user, buffer);
            System.out.println("(pre sorted) 2. Took: " + (System.currentTimeMillis() - transformTime) + "ms");
        }
        // transforming 1000 packets
        {
            long transformTime = System.currentTimeMillis();
            for (int i = 0; i < 1000; i++) {
                translator.getTransformerManager()
                        .translate(Direction.PacketDirection.TO_CLIENT, user, buffer);
            }
            System.out.println("(1000 packets) 3. Took: " + (System.currentTimeMillis() - transformTime) + "ms");
        }
    }

}