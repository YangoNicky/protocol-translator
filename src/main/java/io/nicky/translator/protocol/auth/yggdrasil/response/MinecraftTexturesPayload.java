package io.nicky.auth.yggdrasil.response;

import io.nicky.auth.minecraft.MinecraftProfileTexture;
import java.util.Map;
import java.util.UUID;

/**
 * The type Minecraft textures payload.
 */
public class MinecraftTexturesPayload
{
    private long timestamp;
    private UUID profileId;
    private String profileName;
    private boolean isPublic;
    private Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> textures;

    /**
     * Gets timestamp.
     *
     * @return the timestamp
     */
    public long getTimestamp() {
        return this.timestamp;
    }

    /**
     * Gets profile id.
     *
     * @return the profile id
     */
    public UUID getProfileId() {
        return this.profileId;
    }

    /**
     * Gets profile name.
     *
     * @return the profile name
     */
    public String getProfileName() {
        return this.profileName;
    }

    /**
     * Is public boolean.
     *
     * @return the boolean
     */
    public boolean isPublic() {
        return this.isPublic;
    }

    /**
     * Gets textures.
     *
     * @return the textures
     */
    public Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> getTextures() {
        return this.textures;
    }
}
