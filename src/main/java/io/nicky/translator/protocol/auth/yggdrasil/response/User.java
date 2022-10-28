package io.nicky.auth.yggdrasil.response;

import io.nicky.auth.properties.PropertyMap;

/**
 * The type User.
 */
public class User
{
    private String id;
    private PropertyMap properties;

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
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
