package io.nicky.auth.yggdrasil.request;

import io.nicky.auth.yggdrasil.YggdrasilUserAuthentication;
import io.nicky.auth.Agent;

/**
 * The type Authentication request.
 */
public class AuthenticationRequest
{
    private Agent agent;
    private String username;
    private String password;
    private String clientToken;
    private boolean requestUser;

    /**
     * Instantiates a new Authentication request.
     *
     * @param authenticationService the authentication service
     * @param username              the username
     * @param password              the password
     */
    public AuthenticationRequest(final YggdrasilUserAuthentication authenticationService, final String username, final String password) {
        super();
        this.requestUser = true;
        this.agent = authenticationService.getAgent();
        this.username = username;
        this.clientToken = authenticationService.getAuthenticationService().getClientToken();
        this.password = password;
    }
}
