package io.nicky.auth;

import io.nicky.auth.GameProfile;

/**
 * The interface Profile lookup callback.
 */
public interface ProfileLookupCallback
{
    /**
     * On profile lookup succeeded.
     *
     * @param p0 the p 0
     */
    void onProfileLookupSucceeded(GameProfile p0);

    /**
     * On profile lookup failed.
     *
     * @param p0 the p 0
     * @param p1 the p 1
     */
    void onProfileLookupFailed(GameProfile p0, Exception p1);
}
