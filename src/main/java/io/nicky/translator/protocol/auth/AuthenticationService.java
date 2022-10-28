package io.nicky.auth;

import io.nicky.auth.GameProfileRepository;
import io.nicky.auth.minecraft.MinecraftSessionService;
import io.nicky.auth.UserAuthentication;
import io.nicky.auth.Agent;

/**
 * The interface Authentication service.
 */
public interface AuthenticationService
{
    /**
     * Create user authentication user authentication.
     *
     * @param p0 the p 0
     * @return the user authentication
     */
    UserAuthentication createUserAuthentication(Agent p0);

    /**
     * Create minecraft session service minecraft session service.
     *
     * @return the minecraft session service
     */
    MinecraftSessionService createMinecraftSessionService();

    /**
     * Create profile repository game profile repository.
     *
     * @return the game profile repository
     */
    GameProfileRepository createProfileRepository();
}
