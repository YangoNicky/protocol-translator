package io.nicky.translator.protocol.protocol.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.nicky.translator.protocol.protocol.json.component.formatter.DefaultJsonFormatter;
import io.nicky.translator.protocol.protocol.json.component.formatter.JsonFormatter;
import io.nicky.translator.protocol.protocol.json.component.gson.EnumTypeAdapterFactory;

public final class TransformableComponent {

    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapterFactory(new EnumTypeAdapterFactory())
            .disableJdkUnsafe()
            .create();

    private Gson gson = GSON;
    private JsonFormatter formatter = new DefaultJsonFormatter();

    public TransformableComponent() {
    }

    public Gson getGson() {
        return gson;
    }

    public JsonFormatter getFormatter() {
        return formatter;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public void setFormatter(JsonFormatter formatter) {
        this.formatter = formatter;
    }
}
