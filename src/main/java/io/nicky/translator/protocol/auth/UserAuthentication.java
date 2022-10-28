package io.nicky.auth;

import io.nicky.auth.UserType;
import io.nicky.auth.properties.PropertyMap;

import java.util.List;
import java.util.Map;
import io.nicky.auth.GameProfile;
import io.nicky.auth.exceptions.AuthenticationException;

/**
 * The interface User authentication.
 */
public interface UserAuthentication
{
    /**
     * Can log in boolean.
     *
     * @return the boolean
     */
    boolean canLogIn();

    /**
     * Log in.
     *
     * @throws AuthenticationException the authentication exception
     */
    void logIn() throws AuthenticationException;

    /**
     * Log out.
     */
    void logOut();

    /**
     * Is logged in boolean.
     *
     * @return the boolean
     */
    boolean isLoggedIn();

    /**
     * Can play online boolean.
     *
     * @return the boolean
     */
    boolean canPlayOnline();

    /**
     * Get available profiles game profile [ ].
     *
     * @return the game profile [ ]
     */
    GameProfile[] getAvailableProfiles();

    /**
     * Gets selected profile.
     *
     * @return the selected profile
     */
    GameProfile getSelectedProfile();

    /**
     * Select game profile.
     *
     * @param p0 the p 0
     * @throws AuthenticationException the authentication exception
     */
    void selectGameProfile(GameProfile p0) throws AuthenticationException;

    /**
     * Load from storage.
     *
     * @param credentials the credentials
     */
    void loadFromStorage(Map<String, List<Map<String, String>>> credentials);

    /**
     * Save for storage map.
     *
     * @return the map
     */
    Map<String, Object> saveForStorage();

    /**
     * Sets username.
     *
     * @param p0 the p 0
     */
    void setUsername(String p0);

    /**
     * Sets password.
     *
     * @param p0 the p 0
     */
    void setPassword(String p0);

    /**
     * Gets authenticated token.
     *
     * @return the authenticated token
     */
    String getAuthenticatedToken();

    /**
     * Gets user id.
     *
     * @return the user id
     */
    String getUserID();

    /**
     * Gets user properties.
     *
     * @return the user properties
     */
    PropertyMap getUserProperties();

    /**
     * Gets user type.
     *
     * @return the user type
     */
    UserType getUserType();
}
