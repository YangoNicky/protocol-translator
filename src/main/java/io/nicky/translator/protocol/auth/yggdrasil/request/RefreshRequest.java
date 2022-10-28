package io.nicky.auth.yggdrasil.request;

import io.nicky.auth.GameProfile;
import io.nicky.auth.yggdrasil.YggdrasilUserAuthentication;

/**
 * The type Refresh request.
 */
public class RefreshRequest {
    private String clientToken;
    private String accessToken;
    private GameProfile selectedProfile;
    private boolean requestUser;

    /**
     * Instantiates a new Refresh request.
     *
     * @param authenticationService the authentication service
     */
    public RefreshRequest(final YggdrasilUserAuthentication authenticationService) {
        this(authenticationService, null);
    }

    /**
     * Instantiates a new Refresh request.
     *
     * @param authenticationService the authentication service
     * @param profile               the profile
     */
    public RefreshRequest(final YggdrasilUserAuthentication authenticationService, final GameProfile profile) {
        super();
        this.requestUser = true;
        this.clientToken = authenticationService.getAuthenticationService().getClientToken();
        this.accessToken = authenticationService.getAuthenticatedToken();
        this.selectedProfile = profile;
    }
}
