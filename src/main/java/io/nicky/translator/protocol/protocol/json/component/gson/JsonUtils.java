package io.nicky.translator.protocol.protocol.json.component.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;

@SuppressWarnings("all")
public class JsonUtils {

    public static boolean isString(JsonObject jsonObject, String key) {
        return !isJsonPrimitive(jsonObject, key) ? false :
                jsonObject.getAsJsonPrimitive(key).isString();
    }

    public static boolean isString(JsonElement jsonElement) {
        return !jsonElement.isJsonPrimitive() ? false :
                jsonElement.getAsJsonPrimitive().isString();
    }

    public static boolean isBoolean(JsonObject jsonObject, String key) {
        return !isJsonPrimitive(jsonObject, key) ? false :
                jsonObject.getAsJsonPrimitive(key).isBoolean();
    }

    public static boolean isJsonArray(JsonObject jsonObject, String key) {
        return !hasField(jsonObject, key) ? false :
                jsonObject.get(key).isJsonArray();
    }

    public static boolean isJsonPrimitive(JsonObject jsonObject, String key) {
        return !hasField(jsonObject, key) ? false :
                jsonObject.get(key).isJsonPrimitive();
    }

    public static boolean hasField(JsonObject jsonObject, String key) {
        return jsonObject == null ? false :
                jsonObject.get(key) != null;
    }

    public static String getString(JsonElement jsonObject, String key) {
        if (jsonObject.isJsonPrimitive()) {
            return jsonObject.getAsString();
        } else {
            throw new JsonSyntaxException("Expected " + key + " to be a string, was " + toString(jsonObject));
        }
    }

    public static String getString(JsonObject jsonObject, String key) {
        if (jsonObject.has(key)) {
            return getString(jsonObject.get(key), key);
        } else {
            throw new JsonSyntaxException("Missing " + key + ", expected to find a string");
        }
    }

    public static String getString(JsonObject jsonObject, String key, String defaultValue) {
        return jsonObject.has(key) ? getString(jsonObject.get(key), key) : defaultValue;
    }

    public static boolean getBoolean(JsonElement jsonObject, String defaultValue) {
        if (jsonObject.isJsonPrimitive()) {
            return jsonObject.getAsBoolean();
        } else {
            throw new JsonSyntaxException("Expected " + defaultValue + " to be a Boolean, was " + toString(jsonObject));
        }
    }

    public static boolean getBoolean(JsonObject jsonObject, String defaultValue) {
        if (jsonObject.has(defaultValue)) {
            return getBoolean(jsonObject.get(defaultValue), defaultValue);
        } else {
            throw new JsonSyntaxException("Missing " + defaultValue + ", expected to find a Boolean");
        }
    }

    public static boolean getBoolean(JsonObject jsonObject, String key, boolean p_151209_2_) {
        return jsonObject.has(key) ? getBoolean(jsonObject.get(key), key) : p_151209_2_;
    }

    public static float getFloat(JsonElement jsonObject, String key) {
        if (jsonObject.isJsonPrimitive() && jsonObject.getAsJsonPrimitive().isNumber()) {
            return jsonObject.getAsFloat();
        } else {
            throw new JsonSyntaxException("Expected " + key + " to be a Float, was " + toString(jsonObject));
        }
    }

    public static float getFloat(JsonObject jsonObject, String key) {
        if (jsonObject.has(key)) {
            return getFloat(jsonObject.get(key), key);
        } else {
            throw new JsonSyntaxException("Missing " + key + ", expected to find a Float");
        }
    }

    public static float getFloat(JsonObject jsonObject, String key, float defaultValue) {
        return jsonObject.has(key) ? getFloat(jsonObject.get(key), key) : defaultValue;
    }

    public static int getInt(JsonElement jsonElement, String key) {
        if (jsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isNumber()) {
            return jsonElement.getAsInt();
        } else {
            throw new JsonSyntaxException("Expected " + key + " to be a Int, was " + toString(jsonElement));
        }
    }

    public static int getInt(JsonObject jsonObject, String key) {
        if (jsonObject.has(key)) {
            return getInt(jsonObject.get(key), key);
        } else {
            throw new JsonSyntaxException("Missing " + key + ", expected to find a Int");
        }
    }

    public static int getInt(JsonObject jsonObject, String key, int defaultValue) {
        return jsonObject.has(key) ? getInt(jsonObject.get(key), key) : defaultValue;
    }

    public static JsonObject getJsonObject(JsonElement jsonElement, String key) {
        if (jsonElement.isJsonObject()) {
            return jsonElement.getAsJsonObject();
        } else {
            throw new JsonSyntaxException("Expected " + key + " to be a JsonObject, was " + toString(jsonElement));
        }
    }

    public static JsonObject getJsonObject(JsonObject base, String key) {
        if (base.has(key)) {
            return getJsonObject(base.get(key), key);
        } else {
            throw new JsonSyntaxException("Missing " + key + ", expected to find a JsonObject");
        }
    }

    public static JsonObject getJsonObject(JsonObject jsonObject, String key, JsonObject defaultValue) {
        return jsonObject.has(key) ? getJsonObject(jsonObject.get(key), key) : defaultValue;
    }

    public static JsonArray getJsonArray(JsonElement jsonElement, String key) {
        if (jsonElement.isJsonArray()) {
            return jsonElement.getAsJsonArray();
        } else {
            throw new JsonSyntaxException("Expected " + key + " to be a JsonArray, was " + toString(jsonElement));
        }
    }

    public static JsonArray getJsonArray(JsonObject jsonObject, String key) {
        if (jsonObject.has(key)) {
            return getJsonArray(jsonObject.get(key), key);
        } else {
            throw new JsonSyntaxException("Missing " + key + ", expected to find a JsonArray");
        }
    }

    public static JsonArray getJsonArray(JsonObject jsonObject, String key, JsonArray defaultValue) {
        return jsonObject.has(key) ? getJsonArray(jsonObject.get(key), key) : defaultValue;
    }

    public static String toString(JsonElement jsonElement) {
        String string = String.valueOf(jsonElement);

        if (jsonElement == null) {
            return "null (missing)";
        } else if (jsonElement.isJsonNull()) {
            return "null (json)";
        } else if (jsonElement.isJsonArray()) {
            return "an array (" + string + ")";
        } else if (jsonElement.isJsonObject()) {
            return "an object (" + string + ")";
        } else {
            if (jsonElement.isJsonPrimitive()) {
                JsonPrimitive jsonprimitive = jsonElement.getAsJsonPrimitive();

                if (jsonprimitive.isNumber()) {
                    return "a number (" + string + ")";
                }

                if (jsonprimitive.isBoolean()) {
                    return "a boolean (" + string + ")";
                }
            }

            return string;
        }
    }
}
