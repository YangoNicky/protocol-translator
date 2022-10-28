package io.nicky.auth.minecraft;

import org.apache.commons.lang3.builder.ToStringBuilder;
import java.util.Map;

/**
 * The type Minecraft profile texture.
 */
public class MinecraftProfileTexture
{
    private final String url;
    private final Map<String, String> metadata;

    /**
     * Instantiates a new Minecraft profile texture.
     *
     * @param url      the url
     * @param metadata the metadata
     */
    public MinecraftProfileTexture(final String url, final Map<String, String> metadata) {
        super();
        this.url = url;
        this.metadata = metadata;
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Gets metadata.
     *
     * @param key the key
     * @return the metadata
     */
    public String getMetadata(final String key) {
        if (this.metadata == null) {
            return null;
        }
        return this.metadata.get(key);
    }

    /**
     * Gets hash.
     *
     * @return the hash
     */
    public String getHash() {
        return FilenameUtils.getBaseName(this.url);
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("url", this.url).append("hash", this.getHash()).toString();
    }

    /**
     * The enum Type.
     */
    public enum Type
    {
        /**
         * Skin type.
         */
        SKIN,
        /**
         * Cape type.
         */
        CAPE
    }
}
