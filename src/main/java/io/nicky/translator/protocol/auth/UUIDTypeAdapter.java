package io.nicky.auth;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.UUID;

/**
 * The type Uuid type adapter.
 */
public class UUIDTypeAdapter extends TypeAdapter<UUID> {
    /**
     * From uuid string.
     *
     * @param value the value
     * @return the string
     */
    public static String fromUUID(final UUID value) {
        return value.toString().replace("-", "");
    }

    /**
     * From string uuid.
     *
     * @param input the input
     * @return the uuid
     */
    public static UUID fromString(final String input) {
        return UUID.fromString(input.replaceFirst("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5"));
    }

    public void write(final JsonWriter out, final UUID value) throws IOException {
        out.value(fromUUID(value));
    }

    public UUID read(final JsonReader in) throws IOException {
        return fromString(in.nextString());
    }
}
