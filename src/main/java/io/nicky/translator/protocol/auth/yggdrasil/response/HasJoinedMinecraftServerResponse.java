package io.nicky.auth.yggdrasil.response;

import io.nicky.auth.properties.PropertyMap;
import java.util.UUID;
import io.nicky.auth.yggdrasil.response.Response;

/**
 * The type Has joined minecraft server response.
 */
public class HasJoinedMinecraftServerResponse extends Response
{
    private UUID id;
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
     * Gets properties.
     *
     * @return the properties
     */
    public PropertyMap getProperties() {
        return this.properties;
    }
}
