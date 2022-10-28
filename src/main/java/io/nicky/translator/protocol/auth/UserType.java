package io.nicky.auth;

import java.util.HashMap;
import java.util.Map;

/**
 * The enum User type.
 */
public enum UserType
{
    /**
     * Legacy user type.
     */
    LEGACY("legacy"),
    /**
     * Mojang user type.
     */
    MOJANG("mojang");
    
    private static final Map<String, UserType> BY_NAME;
    private final String name;
    
    private UserType(final String name) {
        this.name = name;
    }

    /**
     * By name user type.
     *
     * @param name the name
     * @return the user type
     */
    public static UserType byName(final String name) {
        return UserType.BY_NAME.get(name.toLowerCase());
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }
    
    static {
        BY_NAME = new HashMap<String, UserType>();
        for (final UserType type : values()) {
            UserType.BY_NAME.put(type.name, type);
        }
    }
}
