package io.nicky.auth.legacy;

import io.nicky.auth.AuthenticationService;
import io.nicky.auth.minecraft.MinecraftProfileTexture;
import java.util.Map;
import java.io.IOException;
import io.nicky.auth.exceptions.AuthenticationUnavailableException;
import io.nicky.auth.exceptions.AuthenticationException;
import java.util.HashMap;
import io.nicky.auth.GameProfile;
import io.nicky.auth.HttpAuthenticationService;
import io.nicky.auth.legacy.LegacyAuthenticationService;
import java.net.URL;
import io.nicky.auth.minecraft.HttpMinecraftSessionService;

/**
 * The type Legacy minecraft session service.
 */
public class LegacyMinecraftSessionService extends HttpMinecraftSessionService
{
    private static final String BASE_URL = "http://session.minecraft.net/game/";
    private static final URL JOIN_URL;
    private static final URL CHECK_URL;

    /**
     * Instantiates a new Legacy minecraft session service.
     *
     * @param authenticationService the authentication service
     */
    protected LegacyMinecraftSessionService(final LegacyAuthenticationService authenticationService) {
        super(authenticationService);
    }
    
    @Override
    public void joinServer(final GameProfile profile, final String authenticationToken, final String serverId) throws AuthenticationException {
        final Map<String, Object> arguments = new HashMap<String, Object>();
        arguments.put("user", profile.getName());
        arguments.put("sessionId", authenticationToken);
        arguments.put("serverId", serverId);
        final URL url = HttpAuthenticationService.concatenateURL(LegacyMinecraftSessionService.JOIN_URL, HttpAuthenticationService.buildQuery(arguments));
        try {
            final String response = this.getAuthenticationService().performGetRequest(url);
            if (!response.equals("OK")) {
                throw new AuthenticationException(response);
            }
        }
        catch (IOException e) {
            throw new AuthenticationUnavailableException(e);
        }
    }
    
    @Override
    public GameProfile hasJoinedServer(final GameProfile user, final String serverId) throws AuthenticationUnavailableException {
        final Map<String, Object> arguments = new HashMap<String, Object>();
        arguments.put("user", user.getName());
        arguments.put("serverId", serverId);
        final URL url = HttpAuthenticationService.concatenateURL(LegacyMinecraftSessionService.CHECK_URL, HttpAuthenticationService.buildQuery(arguments));
        try {
            final String response = this.getAuthenticationService().performGetRequest(url);
            return response.equals("YES") ? user : null;
        }
        catch (IOException e) {
            throw new AuthenticationUnavailableException(e);
        }
    }
    
    @Override
    public Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> getTextures(final GameProfile profile, final boolean requireSecure) {
        return new HashMap<MinecraftProfileTexture.Type, MinecraftProfileTexture>();
    }
    
    @Override
    public GameProfile fillProfileProperties(final GameProfile profile, final boolean requireSecure) {
        return profile;
    }
    
    @Override
    public LegacyAuthenticationService getAuthenticationService() {
        return (LegacyAuthenticationService)super.getAuthenticationService();
    }
    
    static {
        JOIN_URL = HttpAuthenticationService.constantURL("http://session.minecraft.net/game/joinserver.jsp");
        CHECK_URL = HttpAuthenticationService.constantURL("http://session.minecraft.net/game/checkserver.jsp");
    }
}
