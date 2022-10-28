package io.nicky.auth.yggdrasil;

import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import io.nicky.auth.*;
import io.nicky.auth.exceptions.AuthenticationException;
import io.nicky.auth.yggdrasil.response.ProfileSearchResultsResponse;

import java.util.List;
import java.util.Set;

/**
 * The type Yggdrasil game profile repository.
 */
public class YggdrasilGameProfileRepository implements GameProfileRepository {
    private final YggdrasilAuthenticationService authenticationService;

    /**
     * Instantiates a new Yggdrasil game profile repository.
     *
     * @param authenticationService the authentication service
     */
    public YggdrasilGameProfileRepository(final YggdrasilAuthenticationService authenticationService) {
        super();
        this.authenticationService = authenticationService;
    }

    @Override
    public void findProfilesByNames(final String[] names, final Agent agent, final ProfileLookupCallback callback) {
        final Set<String> criteria = Sets.newHashSet();
        for (final String name : names) {
            if (!Strings.isNullOrEmpty(name)) {
                criteria.add(name.toLowerCase());
            }
        }
        final int page = 0;
        for (final List<String> request : Iterables.partition(criteria, 2)) {
            int failCount = 0;
            boolean failed;
            do {
                failed = false;
                try {
                    final ProfileSearchResultsResponse response = this.authenticationService.makeRequest(HttpAuthenticationService.constantURL("https://api.mojang.com/profiles/" + agent.getName().toLowerCase()), request, ProfileSearchResultsResponse.class);
                    failCount = 0;
                    final Set<String> missing = (Set<String>) Sets.newHashSet((Iterable) request);
                    for (final GameProfile profile : response.getProfiles()) {
                        missing.remove(profile.getName().toLowerCase());
                        callback.onProfileLookupSucceeded(profile);
                    }
                    for (final String name2 : missing) {
                        callback.onProfileLookupFailed(new GameProfile(null, name2), new ProfileNotFoundException("Server did not find the requested profile"));
                    }
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException ignored) { }
                } catch (AuthenticationException e) {
                    if (++failCount == 3) {
                        for (final String name3 : request) {
                            callback.onProfileLookupFailed(new GameProfile(null, name3), e);
                        }
                    } else {
                        try {
                            Thread.sleep(750L);
                        } catch (InterruptedException ignored) { }
                        failed = true;
                    }
                }
            } while (failed);
        }
    }

}
