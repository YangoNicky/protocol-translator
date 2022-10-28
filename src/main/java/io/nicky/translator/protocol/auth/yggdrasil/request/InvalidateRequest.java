package io.nicky.auth.yggdrasil.request;

import io.nicky.auth.yggdrasil.YggdrasilUserAuthentication;

/**
 * The type Invalidate request.
 */
public class InvalidateRequest
{
    private String accessToken;
    private String clientToken;

    /**
     * Instantiates a new Invalidate request.
     *
     * @param authenticationService the authentication service
     */
    public InvalidateRequest(final YggdrasilUserAuthentication authenticationService) {
        super();
        this.accessToken = authenticationService.getAuthenticatedToken();
        this.clientToken = authenticationService.getAuthenticationService().getClientToken();
    }
}
