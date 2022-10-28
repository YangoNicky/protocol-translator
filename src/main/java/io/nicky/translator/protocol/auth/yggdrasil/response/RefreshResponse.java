package io.nicky.auth.yggdrasil.response;

import io.nicky.auth.yggdrasil.response.User;
import io.nicky.auth.GameProfile;
import io.nicky.auth.yggdrasil.response.Response;

/**
 * The type Refresh response.
 */
public class RefreshResponse extends Response
{
    private String accessToken;
    private String clientToken;
    private GameProfile selectedProfile;
    private GameProfile[] availableProfiles;
    private User user;

    /**
     * Gets access token.
     *
     * @return the access token
     */
    public String getAccessToken() {
        return this.accessToken;
    }

    /**
     * Gets client token.
     *
     * @return the client token
     */
    public String getClientToken() {
        return this.clientToken;
    }

    /**
     * Get available profiles game profile [ ].
     *
     * @return the game profile [ ]
     */
    public GameProfile[] getAvailableProfiles() {
        return this.availableProfiles;
    }

    /**
     * Gets selected profile.
     *
     * @return the selected profile
     */
    public GameProfile getSelectedProfile() {
        return this.selectedProfile;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return this.user;
    }
}
