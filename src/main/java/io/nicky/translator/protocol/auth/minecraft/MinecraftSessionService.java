package io.nicky.auth.minecraft;

import io.nicky.auth.minecraft.MinecraftProfileTexture;
import java.util.Map;
import io.nicky.auth.exceptions.AuthenticationUnavailableException;
import io.nicky.auth.exceptions.AuthenticationException;
import io.nicky.auth.GameProfile;

/**
 * The interface Minecraft session service.
 */
public interface MinecraftSessionService
{
    /**
     * Join server.
     *
     * @param p0 the p 0
     * @param p1 the p 1
     * @param p2 the p 2
     * @throws AuthenticationException the authentication exception
     */
    void joinServer(GameProfile p0, String p1, String p2) throws AuthenticationException;

    /**
     * Has joined server game profile.
     *
     * @param p0 the p 0
     * @param p1 the p 1
     * @return the game profile
     * @throws AuthenticationUnavailableException the authentication unavailable exception
     */
    GameProfile hasJoinedServer(GameProfile p0, String p1) throws AuthenticationUnavailableException;

    /**
     * Gets textures.
     *
     * @param p0 the p 0
     * @param p1 the p 1
     * @return the textures
     */
    Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> getTextures(GameProfile p0, boolean p1);

    /**
     * Fill profile properties game profile.
     *
     * @param p0 the p 0
     * @param p1 the p 1
     * @return the game profile
     */
    GameProfile fillProfileProperties(GameProfile p0, boolean p1);
}
