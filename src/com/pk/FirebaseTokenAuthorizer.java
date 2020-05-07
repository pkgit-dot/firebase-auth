package com.pk.firebaseauth;

/**
 *
 * @author pk
 */
public interface FirebaseTokenAuthorizer {

    /**
     * Verify the firebase token.
     * if token is not passed (or is null) or is not valid then it will return null
     * else return the phone number of the corresponding user
     */
    String verify(String firebaseToken);
}
