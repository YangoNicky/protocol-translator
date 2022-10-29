package io.nicky.translator.protocol.protocol.json.component.formatter;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import io.nicky.translator.protocol.protocol.json.component.Text;

import java.util.Locale;

public class DefaultJsonFormatter implements JsonFormatter {

    @Override
    public JsonElement format(Text text) {
        JsonArray array = new JsonArray();
        this.format(text, array);
        if (array.size() > 1) {
            JsonArray newArray = new JsonArray();
            newArray.add(new JsonPrimitive(""));
            array.forEach(newArray::add);
            array = newArray;
        }

        return array.size() == 1 ? array.get(0) : array;
    }

    @SuppressWarnings("all")
    public void format(final Text text, final JsonArray array) {
        JsonObject object = new JsonObject();
        object.addProperty("text", text.getText());
        if (text.color() != null)
            object.addProperty("color", text.color().name().toLowerCase(Locale.ROOT));

        if (text.clickEvent() != null) {
            JsonObject clickEventObject = new JsonObject();
            clickEventObject.addProperty("action", text.clickEvent().getAction().name().toLowerCase(Locale.ROOT));
            clickEventObject.addProperty("value", text.clickEvent().getValue());
            object.add("clickEvent", clickEventObject);
        }
        array.add(object);


        text.getChildTexts().forEach(childText -> this.format(childText, array));
    }

}
