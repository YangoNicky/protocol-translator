package io.nicky.auth;

import io.nicky.auth.ProfileLookupCallback;
import io.nicky.auth.Agent;

/**
 * The interface Game profile repository.
 */
public interface GameProfileRepository
{
    /**
     * Find profiles by names.
     *
     * @param p0 the p 0
     * @param p1 the p 1
     * @param p2 the p 2
     */
    void findProfilesByNames(String[] p0, Agent p1, ProfileLookupCallback p2);
}
