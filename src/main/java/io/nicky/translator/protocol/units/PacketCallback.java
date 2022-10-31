package io.nicky.translator.protocol.units;

public interface PacketCallback<Element> {

    void accept(Element element);
    void clean();

}
