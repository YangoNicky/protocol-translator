package io.nicky.auth;

import io.nicky.auth.AuthenticationService;
import io.nicky.auth.HttpAuthenticationService;
import io.nicky.auth.BaseUserAuthentication;

/**
 * The type Http user authentication.
 */
public abstract class HttpUserAuthentication extends BaseUserAuthentication
{
    /**
     * Instantiates a new Http user authentication.
     *
     * @param authenticationService the authentication service
     */
    protected HttpUserAuthentication(final HttpAuthenticationService authenticationService) {
        super(authenticationService);
    }
    

    public HttpAuthenticationService getAuthenticationService() {
        return (HttpAuthenticationService)super.getAuthenticationService();
    }
}
