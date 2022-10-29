package io.nicky.translator.protocol.protocol.json.component.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class EnumTypeAdapterFactory implements TypeAdapterFactory {

    @SuppressWarnings("all")
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Class<T> clazz = (Class<T>) typeToken.getRawType();

        if (!clazz.isEnum()) {
            return null;
        } else {
            final Map<String, T> elementMap = new HashMap<>();

            for (T current : clazz.getEnumConstants()) {
                elementMap.put(current instanceof Enum ?
                        ((Enum<?>) current).name().toLowerCase(Locale.US) :
                        current.toString().toLowerCase(Locale.US), current);
            }

            return new EnumTypeAdapter<>(elementMap);
        }
    }

    public static class EnumTypeAdapter<T> extends TypeAdapter<T> {

        private final Map<String, T> elementMap;

        public EnumTypeAdapter(Map<String, T> elementMap) {
            this.elementMap = elementMap;
        }

        @Override
        public void write(JsonWriter jsonWriter, T value) throws IOException {
            if (value == null) {
                jsonWriter.nullValue();
            } else {
                jsonWriter.value(value instanceof Enum ?
                        ((Enum<?>) value).name().toLowerCase(Locale.US) :
                        value.toString().toLowerCase(Locale.US));
            }
        }

        @Override
        public T read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            } else {
                return elementMap.get(jsonReader.nextString());
            }
        }
    }

}
