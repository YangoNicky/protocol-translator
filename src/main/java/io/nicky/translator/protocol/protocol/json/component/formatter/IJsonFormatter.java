package io.nicky.translator.protocol.protocol.json.component.formatter;

import com.google.gson.JsonElement;
import io.nicky.translator.protocol.protocol.json.component.IText;

public interface IJsonFormatter {

    JsonElement format(final IText text);

}
