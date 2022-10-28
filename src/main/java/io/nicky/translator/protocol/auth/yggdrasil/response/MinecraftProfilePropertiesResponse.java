package io.nicky.auth.yggdrasil.response;

import io.nicky.auth.properties.PropertyMap;
import java.util.UUID;
import io.nicky.auth.yggdrasil.response.Response;

/**
 * The type Minecraft profile properties response.
 */
public class MinecraftProfilePropertiesResponse extends Response
{
    private UUID id;
    private String name;
    private PropertyMap properties;

    /**
     * Gets id.
     *
     * @return the id
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets properties.
     *
     * @return the properties
     */
    public PropertyMap getProperties() {
        return this.properties;
    }
}
