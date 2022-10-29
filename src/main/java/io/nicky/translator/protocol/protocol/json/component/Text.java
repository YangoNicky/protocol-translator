package io.nicky.translator.protocol.protocol.json.component;

import com.google.gson.JsonElement;
import io.nicky.translator.protocol.protocol.json.component.events.ClickEvent;
import io.nicky.translator.protocol.protocol.json.component.events.HoverEvent;
import io.nicky.translator.protocol.protocol.json.component.formatter.JsonFormatter;
import io.nicky.translator.protocol.protocol.json.component.types.LiteralText;

import java.util.List;

public interface Text {

    static Text literal(final String literal) {
        return new LiteralText(literal);
    }

    Text append(final Text text);

    Text hoverEvent(final HoverEvent event);

    Text clickEvent(final ClickEvent event);

    Text color(final EnumColor color);

    HoverEvent hoverEvent();

    ClickEvent clickEvent();

    EnumColor color();

    default JsonElement toJson(final JsonFormatter formatter) {
        return formatter.format(this);
    }

    List<Text> getChildTexts();

    String asString(final boolean colored);

    String getText();

    default String asUncoloredString() {
        return this.asString(false);
    }

    default String asColoredString() {
        return this.asString(true);
    }

}
