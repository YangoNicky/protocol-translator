package io.nicky.auth.yggdrasil;

import com.google.common.base.Charsets;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Iterables;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import io.nicky.auth.GameProfile;
import io.nicky.auth.HttpAuthenticationService;
import io.nicky.auth.UUIDTypeAdapter;
import io.nicky.auth.exceptions.AuthenticationException;
import io.nicky.auth.exceptions.AuthenticationUnavailableException;
import io.nicky.auth.minecraft.HttpMinecraftSessionService;
import io.nicky.auth.minecraft.InsecureTextureException;
import io.nicky.auth.minecraft.MinecraftProfileTexture;
import io.nicky.auth.properties.Property;
import io.nicky.auth.yggdrasil.request.JoinMinecraftServerRequest;
import io.nicky.auth.yggdrasil.response.HasJoinedMinecraftServerResponse;
import io.nicky.auth.yggdrasil.response.MinecraftProfilePropertiesResponse;
import io.nicky.auth.yggdrasil.response.MinecraftTexturesPayload;
import io.nicky.auth.yggdrasil.response.Response;
import org.apache.commons.io.IOUtils;

import java.net.URL;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.LogManager;

/**
 * The type Yggdrasil minecraft session service.
 */
public class YggdrasilMinecraftSessionService extends HttpMinecraftSessionService {
    private static final URL JOIN_URL = HttpAuthenticationService.constantURL("https://sessionserver.mojang.com/session/minecraft/join");
    private static final URL CHECK_URL= HttpAuthenticationService.constantURL("https://sessionserver.mojang.com/session/minecraft/hasJoined");
    private final PublicKey publicKey;
    private final Gson gson;
    private final LoadingCache<GameProfile, GameProfile> insecureProfiles;

    /**
     * Instantiates a new Yggdrasil minecraft session service.
     *
     * @param authenticationService the authentication service
     */
    protected YggdrasilMinecraftSessionService(final YggdrasilAuthenticationService authenticationService) {
        super(authenticationService);
        this.gson = new GsonBuilder().registerTypeAdapter(UUID.class, new UUIDTypeAdapter()).create();
        this.insecureProfiles = (LoadingCache<GameProfile, GameProfile>) CacheBuilder.newBuilder().expireAfterWrite(6L, TimeUnit.HOURS).build((CacheLoader) new CacheLoader<GameProfile, GameProfile>() {
            public GameProfile load(final GameProfile key) throws Exception {
                return YggdrasilMinecraftSessionService.this.fillGameProfile(key, false);
            }
        });
        try {
            final X509EncodedKeySpec spec = new X509EncodedKeySpec(IOUtils.toByteArray(YggdrasilMinecraftSessionService.class.getResourceAsStream("/yggdrasil_session_pubkey.der")));
            final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            this.publicKey = keyFactory.generatePublic(spec);
        } catch (Exception e) {
            throw new Error("Missing/invalid yggdrasil public key!");
        }
    }

    @Override
    public void joinServer(final GameProfile profile, final String authenticationToken, final String serverId) throws AuthenticationException {
        final JoinMinecraftServerRequest request = new JoinMinecraftServerRequest();
        request.accessToken = authenticationToken;
        request.selectedProfile = profile.getId();
        request.serverId = serverId;
        this.getAuthenticationService().makeRequest(YggdrasilMinecraftSessionService.JOIN_URL, request, Response.class);
    }

    @Override
    public GameProfile hasJoinedServer(final GameProfile user, final String serverId) throws AuthenticationUnavailableException {
        final Map<String, Object> arguments = new HashMap<String, Object>();
        arguments.put("username", user.getName());
        arguments.put("serverId", serverId);
        final URL url = HttpAuthenticationService.concatenateURL(YggdrasilMinecraftSessionService.CHECK_URL, HttpAuthenticationService.buildQuery(arguments));
        try {
            final HasJoinedMinecraftServerResponse response = this.getAuthenticationService().makeRequest(url, null, HasJoinedMinecraftServerResponse.class);
            if (response != null && response.getId() != null) {
                final GameProfile result = new GameProfile(response.getId(), user.getName());
                if (response.getProperties() != null) {
                    result.getProperties().putAll(response.getProperties());
                }
                return result;
            }
            return null;
        } catch (AuthenticationUnavailableException e) {
            throw e;
        } catch (AuthenticationException e2) {
            return null;
        }
    }

    @Override
    public Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> getTextures(final GameProfile profile, final boolean requireSecure) {
        final Property textureProperty = (Property) Iterables.getFirst(profile.getProperties().get("textures"), (Object) null);
        if (textureProperty == null) {
            return new HashMap<>();
        }
        if (requireSecure) {
            if (!textureProperty.hasSignature()) {
                throw new InsecureTextureException("Signature is missing from textures payload");
            }
            if (!textureProperty.isSignatureValid(this.publicKey)) {
                throw new InsecureTextureException("Textures payload has been tampered with (signature invalid)");
            }
        }
        MinecraftTexturesPayload result;
        try {
            final String json = new String(Base64.getDecoder().decode(textureProperty.getValue()), Charsets.UTF_8);
            result = (MinecraftTexturesPayload) this.gson.fromJson(json, (Class) MinecraftTexturesPayload.class);
        } catch (JsonParseException e) {
            return new HashMap<>();
        }
        return (result.getTextures() == null) ? new HashMap<>() : result.getTextures();
    }

    @Override
    public GameProfile fillProfileProperties(final GameProfile profile, final boolean requireSecure) {
        if (profile.getId() == null) {
            return profile;
        }
        if (!requireSecure) {
            return this.insecureProfiles.getUnchecked(profile);
        }
        return this.fillGameProfile(profile, true);
    }

    /**
     * Fill game profile game profile.
     *
     * @param profile       the profile
     * @param requireSecure the require secure
     * @return the game profile
     */
    protected GameProfile fillGameProfile(final GameProfile profile, final boolean requireSecure) {
        try {
            URL url = HttpAuthenticationService.constantURL("https://sessionserver.mojang.com/session/minecraft/profile/" + UUIDTypeAdapter.fromUUID(profile.getId()));
            url = HttpAuthenticationService.concatenateURL(url, "unsigned=" + !requireSecure);
            final MinecraftProfilePropertiesResponse response = this.getAuthenticationService().makeRequest(url, null, MinecraftProfilePropertiesResponse.class);
            if (response == null) {
                return profile;
            }
            final GameProfile result = new GameProfile(response.getId(), response.getName());
            result.getProperties().putAll(response.getProperties());
            profile.getProperties().putAll(response.getProperties());
            return result;
        } catch (AuthenticationException e) {
            return profile;
        }
    }

    @Override
    public YggdrasilAuthenticationService getAuthenticationService() {
        return (YggdrasilAuthenticationService) super.getAuthenticationService();
    }


}
