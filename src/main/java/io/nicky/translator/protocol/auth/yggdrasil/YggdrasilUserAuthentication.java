package io.nicky.auth.yggdrasil;

import io.nicky.auth.*;
import io.nicky.auth.exceptions.AuthenticationException;
import io.nicky.auth.exceptions.InvalidCredentialsException;
import io.nicky.auth.yggdrasil.request.AuthenticationRequest;
import io.nicky.auth.yggdrasil.request.RefreshRequest;
import io.nicky.auth.yggdrasil.response.AuthenticationResponse;
import io.nicky.auth.yggdrasil.response.RefreshResponse;
import io.nicky.auth.yggdrasil.response.User;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;

/**
 * The type Yggdrasil user authentication.
 */
public class YggdrasilUserAuthentication extends HttpUserAuthentication {

    private static final String BASE_URL = "https://authserver.mojang.com/";
    private static final URL ROUTE_AUTHENTICATE = HttpAuthenticationService.constantURL("https://authserver.mojang.com/authenticate");
    private static final URL ROUTE_REFRESH = HttpAuthenticationService.constantURL("https://authserver.mojang.com/refresh");
    private static final URL ROUTE_VALIDATE = HttpAuthenticationService.constantURL("https://authserver.mojang.com/validate");
    private static final URL ROUTE_INVALIDATE = HttpAuthenticationService.constantURL("https://authserver.mojang.com/invalidate");
    private static final URL ROUTE_SIGNOUT = HttpAuthenticationService.constantURL("https://authserver.mojang.com/signout");
    private static final String STORAGE_KEY_ACCESS_TOKEN = "accessToken";

    private final Agent agent;
    private GameProfile[] profiles;
    private String accessToken;
    private boolean isOnline;


    /**
     * Instantiates a new Yggdrasil user authentication.
     *
     * @param authenticationService the authentication service
     * @param agent                 the agent
     */
    public YggdrasilUserAuthentication(final YggdrasilAuthenticationService authenticationService, final Agent agent) {
        super(authenticationService);
        this.agent = agent;
    }

    @Override
    public boolean canLogIn() {
        return !this.canPlayOnline() && StringUtils.isNotBlank(this.getUsername()) && (StringUtils.isNotBlank(this.getPassword()) || StringUtils.isNotBlank(this.getAuthenticatedToken()));
    }

    @Override
    public void logIn() throws AuthenticationException {
        if (StringUtils.isBlank(this.getUsername())) {
            throw new InvalidCredentialsException("Invalid username");
        }
        if (StringUtils.isNotBlank(this.getAuthenticatedToken())) {
            this.logInWithToken();
        } else {
            if (!StringUtils.isNotBlank(this.getPassword())) {
                throw new InvalidCredentialsException("Invalid password");
            }
            this.logInWithPassword();
        }
    }

    /**
     * Log in with password.
     *
     * @throws AuthenticationException the authentication exception
     */
    protected void logInWithPassword() throws AuthenticationException {
        if (StringUtils.isBlank(this.getUsername())) {
            throw new InvalidCredentialsException("Invalid username");
        }
        if (StringUtils.isBlank(this.getPassword())) {
            throw new InvalidCredentialsException("Invalid password");
        }
        final AuthenticationRequest request = new AuthenticationRequest(this, this.getUsername(), this.getPassword());
        final AuthenticationResponse response = this.getAuthenticationService().makeRequest(YggdrasilUserAuthentication.ROUTE_AUTHENTICATE, request, AuthenticationResponse.class);
        if (!response.getClientToken().equals(this.getAuthenticationService().getClientToken())) {
            throw new AuthenticationException("Server requested we change our client token. Don't know how to handle this!");
        }
        if (response.getSelectedProfile() != null) {
            this.setUserType(response.getSelectedProfile().isLegacy() ? UserType.LEGACY : UserType.MOJANG);
        } else if (ArrayUtils.isNotEmpty((Object[]) response.getAvailableProfiles())) {
            this.setUserType(response.getAvailableProfiles()[0].isLegacy() ? UserType.LEGACY : UserType.MOJANG);
        }
        final User user = response.getUser();
        if (user != null && user.getId() != null) {
            this.setUserid(user.getId());
        } else {
            this.setUserid(this.getUsername());
        }
        this.isOnline = true;
        this.accessToken = response.getAccessToken();
        this.profiles = response.getAvailableProfiles();
        this.setSelectedProfile(response.getSelectedProfile());
        this.getModifiableUserProperties().clear();
        this.updateUserProperties(user);
    }

    /**
     * Update user properties.
     *
     * @param user the user
     */
    protected void updateUserProperties(final User user) {
        if (user == null) {
            return;
        }
        if (user.getProperties() != null) {
            this.getModifiableUserProperties().putAll(user.getProperties());
        }
    }

    /**
     * Log in with token.
     *
     * @throws AuthenticationException the authentication exception
     */
    protected void logInWithToken() throws AuthenticationException {
        if (StringUtils.isBlank(this.getUserID())) {
            if (!StringUtils.isBlank(this.getUsername())) {
                throw new InvalidCredentialsException("Invalid uuid & username");
            }
            this.setUserid(this.getUsername());
        }
        if (StringUtils.isBlank(this.getAuthenticatedToken())) {
            throw new InvalidCredentialsException("Invalid access token");
        }
        final RefreshRequest request = new RefreshRequest(this);
        final RefreshResponse response = this.getAuthenticationService().makeRequest(YggdrasilUserAuthentication.ROUTE_REFRESH, request, RefreshResponse.class);
        if (!response.getClientToken().equals(this.getAuthenticationService().getClientToken())) {
            throw new AuthenticationException("Server requested we change our client token. Don't know how to handle this!");
        }
        if (response.getSelectedProfile() != null) {
            this.setUserType(response.getSelectedProfile().isLegacy() ? UserType.LEGACY : UserType.MOJANG);
        } else if (ArrayUtils.isNotEmpty((Object[]) response.getAvailableProfiles())) {
            this.setUserType(response.getAvailableProfiles()[0].isLegacy() ? UserType.LEGACY : UserType.MOJANG);
        }
        if (response.getUser() != null && response.getUser().getId() != null) {
            this.setUserid(response.getUser().getId());
        } else {
            this.setUserid(this.getUsername());
        }
        this.isOnline = true;
        this.accessToken = response.getAccessToken();
        this.profiles = response.getAvailableProfiles();
        this.setSelectedProfile(response.getSelectedProfile());
        this.getModifiableUserProperties().clear();
        this.updateUserProperties(response.getUser());
    }

    @Override
    public void logOut() {
        super.logOut();
        this.accessToken = null;
        this.profiles = null;
        this.isOnline = false;
    }

    @Override
    public GameProfile[] getAvailableProfiles() {
        return this.profiles;
    }

    @Override
    public boolean isLoggedIn() {
        return StringUtils.isNotBlank(this.accessToken);
    }

    @Override
    public boolean canPlayOnline() {
        return this.isLoggedIn() && this.getSelectedProfile() != null && this.isOnline;
    }

    @Override
    public void selectGameProfile(final GameProfile profile) throws AuthenticationException {
        if (!this.isLoggedIn()) {
            throw new AuthenticationException("Cannot change game profile whilst not logged in");
        }
        if (this.getSelectedProfile() != null) {
            throw new AuthenticationException("Cannot change game profile. You must log out and back in.");
        }
        if (profile == null || !ArrayUtils.contains(this.profiles, profile)) {
            throw new IllegalArgumentException("Invalid profile '" + profile + "'");
        }
        final RefreshRequest request = new RefreshRequest(this, profile);
        final RefreshResponse response = this.getAuthenticationService().makeRequest(YggdrasilUserAuthentication.ROUTE_REFRESH, request, RefreshResponse.class);
        if (!response.getClientToken().equals(this.getAuthenticationService().getClientToken())) {
            throw new AuthenticationException("Server requested we change our client token. Don't know how to handle this!");
        }
        this.isOnline = true;
        this.accessToken = response.getAccessToken();
        this.setSelectedProfile(response.getSelectedProfile());
    }

    @Override
    public void loadFromStorage(final Map<String, List<Map<String, String>>> credentials) {
        super.loadFromStorage(credentials);
        this.accessToken = String.valueOf(credentials.get("accessToken"));
    }

    @Override
    public Map<String, Object> saveForStorage() {
        final Map<String, Object> result = super.saveForStorage();
        if (StringUtils.isNotBlank(this.getAuthenticatedToken())) {
            result.put("accessToken", this.getAuthenticatedToken());
        }
        return result;
    }


    /**
     * Gets session token.
     *
     * @return the session token
     */
    public String getSessionToken() {
        if (this.isLoggedIn() && this.getSelectedProfile() != null && this.canPlayOnline()) {
            return String.format("token:%s:%s", this.getAuthenticatedToken(), this.getSelectedProfile().getId());
        }
        return null;
    }

    @Override
    public String getAuthenticatedToken() {
        return this.accessToken;
    }

    /**
     * Gets agent.
     *
     * @return the agent
     */
    public Agent getAgent() {
        return this.agent;
    }

    @Override
    public String toString() {
        return "YggdrasilAuthenticationService{agent=" + this.agent + ", profiles=" + Arrays.toString(this.profiles) + ", selectedProfile=" + this.getSelectedProfile() + ", username='" + this.getUsername() + '\'' + ", isLoggedIn=" + this.isLoggedIn() + ", userType=" + this.getUserType() + ", canPlayOnline=" + this.canPlayOnline() + ", accessToken='" + this.accessToken + '\'' + ", clientToken='" + this.getAuthenticationService().getClientToken() + '\'' + '}';
    }

    @Override
    public YggdrasilAuthenticationService getAuthenticationService() {
        return (YggdrasilAuthenticationService) super.getAuthenticationService();
    }


}
