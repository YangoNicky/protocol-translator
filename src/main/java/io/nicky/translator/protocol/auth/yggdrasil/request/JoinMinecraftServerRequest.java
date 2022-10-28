package io.nicky.auth.yggdrasil.request;

import java.util.UUID;

/**
 * The type Join minecraft server request.
 */
public class JoinMinecraftServerRequest
{
    /**
     * The Access token.
     */
    public String accessToken;
    /**
     * The Selected profile.
     */
    public UUID selectedProfile;
    /**
     * The Server id.
     */
    public String serverId;
}
