package testing;

import io.nicky.translator.protocol.ProtocolTranslator;

public class TestBootstrap {

    public static void main(String[] args) {
        final ProtocolTranslator translator = new ProtocolTranslator();
        translator.initialize();
    }

}