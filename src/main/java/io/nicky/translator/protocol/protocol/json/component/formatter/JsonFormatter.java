package io.nicky.translator.protocol.protocol.json.component.formatter;

import com.google.gson.JsonElement;
import io.nicky.translator.protocol.protocol.json.component.Text;

public interface JsonFormatter {

    JsonElement format(final Text text);

}
