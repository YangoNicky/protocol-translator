package io.nicky.translator.protocol.protocol;

import java.lang.annotation.*;

@Documented
@SuppressWarnings("unused")
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
public @interface Direction {

    PacketDirection value();

    enum PacketDirection {
        TO_CLIENT,
        TO_SERVER,
    }

}
