package io.nicky.auth.yggdrasil.response;

import com.google.gson.JsonParseException;
import com.google.gson.JsonObject;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonDeserializer;
import io.nicky.auth.GameProfile;
import io.nicky.auth.yggdrasil.response.Response;

import java.lang.reflect.Type;

/**
 * The type Profile search results response.
 */
public class ProfileSearchResultsResponse extends Response
{
    private GameProfile[] profiles;

    /**
     * Get profiles game profile [ ].
     *
     * @return the game profile [ ]
     */
    public GameProfile[] getProfiles() {
        return this.profiles;
    }

    /**
     * The type Serializer.
     */
    public static class Serializer implements JsonDeserializer<ProfileSearchResultsResponse>
    {
        public ProfileSearchResultsResponse deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
            final ProfileSearchResultsResponse result = new ProfileSearchResultsResponse();
            if (json instanceof JsonObject) {
                final JsonObject object = (JsonObject)json;
                if (object.has("error")) {
                    result.setError(object.getAsJsonPrimitive("error").getAsString());
                }
                if (object.has("errorMessage")) {
                    result.setError(object.getAsJsonPrimitive("errorMessage").getAsString());
                }
                if (object.has("cause")) {
                    result.setError(object.getAsJsonPrimitive("cause").getAsString());
                }
            }
            else {
                result.profiles = context.deserialize(json, GameProfile[].class);
            }
            return result;
        }

    }
}
