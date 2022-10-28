package io.nicky.translator.protocol.protocol;

import java.lang.annotation.*;

@Documented
@SuppressWarnings("unused")
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ProtocolId {

    int id();
    ProtocolVersion from();
    ProtocolVersion current();
    ProtocolVersion to();

}
