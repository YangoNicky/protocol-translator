package io.nicky.translator.protocol.protocol.json.component.types;

import io.nicky.translator.protocol.protocol.json.component.EnumColor;
import io.nicky.translator.protocol.protocol.json.component.IText;
import io.nicky.translator.protocol.protocol.json.component.events.IClickEvent;
import io.nicky.translator.protocol.protocol.json.component.events.IHoverEvent;

import java.util.ArrayList;
import java.util.List;

public class LiteralText implements IText {

    private final List<IText> childTexts = new ArrayList<>();
    private final String text;
    private IHoverEvent hoverEvent;
    private IClickEvent clickEvent;
    private EnumColor color;

    public LiteralText(final String text) {
        this.text = text;
    }

    @Override
    public IText append(IText text) {
        this.childTexts.add(text);
        return this;
    }

    @Override
    public IText hoverEvent(IHoverEvent event) {
        this.hoverEvent = event;
        return this;
    }

    @Override
    public IText clickEvent(IClickEvent event) {
        this.clickEvent = event;
        return this;
    }

    @Override
    public IText color(EnumColor color) {
        this.color = color;
        return this;
    }

    @Override
    public IHoverEvent hoverEvent() {
        return this.hoverEvent;
    }

    @Override
    public IClickEvent clickEvent() {
        return this.clickEvent;
    }

    @Override
    public EnumColor color() {
        return this.color;
    }

    @Override
    public List<IText> getChildTexts() {
        return this.childTexts;
    }

    @Override
    public String asString(boolean colored) {
        String string = asStringInternal(this);
        return colored ? EnumColor.toColoredString('§', string) : EnumColor.stripColor(string);
    }

    @Override
    public String getText() {
        return this.text;
    }

    public String asStringInternal(final IText text) {
        StringBuilder stringBuilder = new StringBuilder();
        if (text.color() != null)
            stringBuilder.append(text.color());
        else
            stringBuilder.append(EnumColor.RESET);
        stringBuilder.append(text.getText());
        text.getChildTexts().forEach(child -> stringBuilder.append(this.asStringInternal(child)));
        return stringBuilder.toString();
    }

}
