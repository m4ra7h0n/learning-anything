package com.xjjlearning.springframework.security.jwt;

/**
 * jwt token storage
 *
 */
public interface JwtTokenStorage {


    /**
     * Put jwt token pair.
     *
     * @param jwtTokenPair the jwt token pair
     * @param userId       the user id
     * @return the jwt token pair
     */
    JwtTokenPair put(JwtTokenPair jwtTokenPair, String userId);


    /**
     * Expire.
     *
     * @param userId the user id
     */
    void expire(String userId);


    /**
     * Get jwt token pair.
     *
     * @param userId the user id
     * @return the jwt token pair
     */
    JwtTokenPair get(String userId);

}
