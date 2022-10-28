package io.nicky.auth.minecraft;

import io.nicky.auth.AuthenticationService;
import io.nicky.auth.minecraft.MinecraftSessionService;

/**
 * The type Base minecraft session service.
 */
public abstract class BaseMinecraftSessionService implements MinecraftSessionService
{
    private final AuthenticationService authenticationService;

    /**
     * Instantiates a new Base minecraft session service.
     *
     * @param authenticationService the authentication service
     */
    protected BaseMinecraftSessionService(final AuthenticationService authenticationService) {
        super();
        this.authenticationService = authenticationService;
    }

    /**
     * Gets authentication service.
     *
     * @return the authentication service
     */
    public AuthenticationService getAuthenticationService() {
        return this.authenticationService;
    }
}
