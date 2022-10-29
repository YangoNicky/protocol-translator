package io.nicky.translator.protocol.protocol.json.component.events;

public interface IHoverEvent {

    enum EnumAction {

        SHOW_TEXT,
        SHOW_ITEM,
        SHOW_ENTITY,

        @Deprecated
        SHOW_ARCHIEVEMENT

    }

}
