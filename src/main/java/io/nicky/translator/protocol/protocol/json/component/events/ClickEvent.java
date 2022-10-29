package io.nicky.translator.protocol.protocol.json.component.events;

public interface ClickEvent {

    EnumAction getAction();

    String getValue();

    enum EnumAction {

        OPEN_URL,
        OPEN_FILE,
        RUN_COMMAND,
        SUGGEST_COMMAND,
        COPY_TO_CLIPBOARD

    }


}
