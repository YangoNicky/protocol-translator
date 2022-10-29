package io.nicky.translator.protocol.protocol.json.component;

import com.google.gson.JsonElement;
import io.nicky.translator.protocol.protocol.json.component.events.IClickEvent;
import io.nicky.translator.protocol.protocol.json.component.events.IHoverEvent;
import io.nicky.translator.protocol.protocol.json.component.formatter.DefaultJsonFormatter;
import io.nicky.translator.protocol.protocol.json.component.types.LiteralText;

import java.util.List;

public interface IText {

    static IText literal(final String literal) {
        return new LiteralText(literal);
    }

    IText append(final IText text);

    IText hoverEvent(final IHoverEvent event);

    IText clickEvent(final IClickEvent event);

    IText color(final EnumColor color);

    IHoverEvent hoverEvent();

    IClickEvent clickEvent();

    EnumColor color();

    default JsonElement toJson() {
        return DefaultJsonFormatter.INSTANCE.format(this);
    }


    List<IText> getChildTexts();

    String asString(final boolean colored);

    String getText();

    default String asUncoloredString() {
        return this.asString(false);
    }

    default String asColoredString() {
        return this.asString(true);
    }

}
