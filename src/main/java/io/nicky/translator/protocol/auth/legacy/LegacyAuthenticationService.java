package io.nicky.auth.legacy;

import io.nicky.auth.UserAuthentication;
import io.nicky.auth.minecraft.MinecraftSessionService;
import io.nicky.auth.GameProfileRepository;
import io.nicky.auth.legacy.LegacyMinecraftSessionService;
import org.apache.commons.lang3.Validate;
import io.nicky.auth.legacy.LegacyUserAuthentication;
import io.nicky.auth.Agent;
import java.net.Proxy;
import io.nicky.auth.HttpAuthenticationService;

/**
 * The type Legacy authentication service.
 */
public class LegacyAuthenticationService extends HttpAuthenticationService
{
    /**
     * Instantiates a new Legacy authentication service.
     *
     * @param proxy the proxy
     */
    protected LegacyAuthenticationService(final Proxy proxy) {
        super(proxy);
    }
    
    @Override
    public LegacyUserAuthentication createUserAuthentication(final Agent agent) {
        Validate.notNull((Object)agent);
        if (agent != Agent.MINECRAFT) {
            throw new IllegalArgumentException("Legacy authentication cannot handle anything but Minecraft");
        }
        return new LegacyUserAuthentication(this);
    }
    
    @Override
    public LegacyMinecraftSessionService createMinecraftSessionService() {
        return new LegacyMinecraftSessionService(this);
    }
    
    @Override
    public GameProfileRepository createProfileRepository() {
        throw new UnsupportedOperationException("Legacy authentication service has no profile repository");
    }
}
