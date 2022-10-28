package io.nicky.auth.minecraft;

import io.nicky.auth.AuthenticationService;
import io.nicky.auth.HttpAuthenticationService;
import io.nicky.auth.minecraft.BaseMinecraftSessionService;

/**
 * The type Http minecraft session service.
 */
public abstract class HttpMinecraftSessionService extends BaseMinecraftSessionService
{
    /**
     * Instantiates a new Http minecraft session service.
     *
     * @param authenticationService the authentication service
     */
    protected HttpMinecraftSessionService(final HttpAuthenticationService authenticationService) {
        super(authenticationService);
    }
    
    @Override
    public HttpAuthenticationService getAuthenticationService() {
        return (HttpAuthenticationService)super.getAuthenticationService();
    }
}
