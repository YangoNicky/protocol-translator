package io.nicky.translator.protocol.protocol.json.component.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class JsonArrayUtils {

    public static JsonArray appendFirst(final JsonElement element, final JsonArray array) {
        JsonArray newArray = new JsonArray();
        newArray.add(element);
        array.forEach(newArray::add);
        return newArray;
    }

}
