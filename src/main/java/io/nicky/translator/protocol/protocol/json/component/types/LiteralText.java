package io.nicky.translator.protocol.protocol.json.component.types;

import io.nicky.translator.protocol.protocol.json.component.EnumColor;
import io.nicky.translator.protocol.protocol.json.component.Text;
import io.nicky.translator.protocol.protocol.json.component.events.ClickEvent;
import io.nicky.translator.protocol.protocol.json.component.events.HoverEvent;

import java.util.ArrayList;
import java.util.List;

public class LiteralText implements Text {

    private final List<Text> childTexts = new ArrayList<>();
    private final String text;
    private HoverEvent hoverEvent;
    private ClickEvent clickEvent;
    private EnumColor color;

    public LiteralText(final String text) {
        this.text = text;
    }

    @Override
    public Text append(Text text) {
        this.childTexts.add(text);
        return this;
    }

    @Override
    public Text hoverEvent(HoverEvent event) {
        this.hoverEvent = event;
        return this;
    }

    @Override
    public Text clickEvent(ClickEvent event) {
        this.clickEvent = event;
        return this;
    }

    @Override
    public Text color(EnumColor color) {
        this.color = color;
        return this;
    }

    @Override
    public HoverEvent hoverEvent() {
        return this.hoverEvent;
    }

    @Override
    public ClickEvent clickEvent() {
        return this.clickEvent;
    }

    @Override
    public EnumColor color() {
        return this.color;
    }

    @Override
    public List<Text> getChildTexts() {
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

    public String asStringInternal(final Text text) {
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
