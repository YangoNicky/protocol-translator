package io.nicky.auth;

import io.nicky.auth.properties.PropertyMap;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.StringUtils;
import java.util.UUID;

/**
 * The type Game profile.
 */
public class GameProfile
{
    private final UUID id;
    private final String name;
    private final PropertyMap properties;
    private boolean legacy;

    /**
     * Instantiates a new Game profile.
     *
     * @param id   the id
     * @param name the name
     */
    public GameProfile(final UUID id, final String name) {
        super();
        this.properties = new PropertyMap();
        if (id == null && StringUtils.isBlank((CharSequence)name)) {
            throw new IllegalArgumentException("Name and ID cannot both be blank");
        }
        this.id = id;
        this.name = name;
    }

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

    /**
     * Is complete boolean.
     *
     * @return the boolean
     */
    public boolean isComplete() {
        return this.id != null && StringUtils.isNotBlank((CharSequence)this.getName());
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final GameProfile that = (GameProfile)o;
        Label_0062: {
            if (this.id != null) {
                if (this.id.equals(that.id)) {
                    break Label_0062;
                }
            }
            else if (that.id == null) {
                break Label_0062;
            }
            return false;
        }
        if (this.name != null) {
            if (this.name.equals(that.name)) {
                return true;
            }
        }
        else if (that.name == null) {
            return true;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        int result = (this.id != null) ? this.id.hashCode() : 0;
        result = 31 * result + ((this.name != null) ? this.name.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder((Object)this).append("id", (Object)this.id).append("name", (Object)this.name).append("properties", (Object)this.properties).append("legacy", this.legacy).toString();
    }

    /**
     * Is legacy boolean.
     *
     * @return the boolean
     */
    public boolean isLegacy() {
        return this.legacy;
    }
}
