package io.nicky.auth.yggdrasil;

import com.google.gson.*;
import io.nicky.auth.*;
import io.nicky.auth.exceptions.AuthenticationException;
import io.nicky.auth.exceptions.AuthenticationUnavailableException;
import io.nicky.auth.exceptions.InvalidCredentialsException;
import io.nicky.auth.exceptions.UserMigratedException;
import io.nicky.auth.minecraft.MinecraftSessionService;
import io.nicky.auth.properties.PropertyMap;
import io.nicky.auth.yggdrasil.response.ProfileSearchResultsResponse;
import io.nicky.auth.yggdrasil.response.Response;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Proxy;
import java.net.URL;
import java.util.UUID;

/**
 * The type Yggdrasil authentication service.
 */
public class YggdrasilAuthenticationService extends HttpAuthenticationService {
    private final String clientToken;
    private final Gson gson;

    /**
     * Instantiates a new Yggdrasil authentication service.
     *
     * @param proxy       the proxy
     * @param clientToken the client token
     */
    public YggdrasilAuthenticationService(final Proxy proxy, final String clientToken) {
        super(proxy);
        this.clientToken = clientToken;
        final GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(GameProfile.class, new GameProfileSerializer());
        builder.registerTypeAdapter(PropertyMap.class, new PropertyMap.Serializer());
        builder.registerTypeAdapter(UUID.class, new UUIDTypeAdapter());
        builder.registerTypeAdapter(ProfileSearchResultsResponse.class, new ProfileSearchResultsResponse.Serializer());
        this.gson = builder.create();
    }

    @Override
    public UserAuthentication createUserAuthentication(final Agent agent) {
        return new YggdrasilUserAuthentication(this, agent);
    }

    @Override
    public MinecraftSessionService createMinecraftSessionService() {
        return new YggdrasilMinecraftSessionService(this);
    }

    @Override
    public GameProfileRepository createProfileRepository() {
        return new YggdrasilGameProfileRepository(this);
    }

    /**
     * Make request t.
     *
     * @param <T>      the type parameter
     * @param url      the url
     * @param input    the input
     * @param classOfT the class of t
     * @return the t
     * @throws AuthenticationException the authentication exception
     */
    protected <T extends Response> T makeRequest(final URL url, final Object input, final Class<T> classOfT) throws AuthenticationException {
        try {
            final String jsonResult = (input == null) ? this.performGetRequest(url) : this.performPostRequest(url,
                    this.gson.toJson(input), "application/json");
            final T result = (T) this.gson.fromJson(jsonResult, (Class) classOfT);
            if (result == null) {
                return null;
            }
            if (!StringUtils.isNotBlank(result.getError())) {
                return result;
            }
            if ("UserMigratedException".equals(result.getCause())) {
                throw new UserMigratedException(result.getErrorMessage());
            }
            if (result.getError().equals("ForbiddenOperationException")) {
                throw new InvalidCredentialsException(result.getErrorMessage());
            }
            throw new AuthenticationException(result.getErrorMessage());
        } catch (IOException | IllegalStateException | JsonParseException e) {
            throw new AuthenticationUnavailableException("Cannot contact authentication server", e);
        }
    }

    /**
     * Gets client token.
     *
     * @return the client token
     */
    public String getClientToken() {
        return this.clientToken;
    }

    private static class GameProfileSerializer implements JsonSerializer<GameProfile>, JsonDeserializer<GameProfile> {
        public GameProfile deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
            final JsonObject object = (JsonObject) json;
            final UUID id = object.has("id") ? ((UUID) context.deserialize(object.get("id"), UUID.class)) : null;
            final String name = object.has("name") ? object.getAsJsonPrimitive("name").getAsString() : null;
            return new GameProfile(id, name);
        }

        public JsonElement serialize(final GameProfile src, final Type typeOfSrc, final JsonSerializationContext context) {
            final JsonObject result = new JsonObject();
            if (src.getId() != null) {
                result.add("id", context.serialize(src.getId()));
            }
            if (src.getName() != null) {
                result.addProperty("name", src.getName());
            }
            return result;
        }
    }
}
