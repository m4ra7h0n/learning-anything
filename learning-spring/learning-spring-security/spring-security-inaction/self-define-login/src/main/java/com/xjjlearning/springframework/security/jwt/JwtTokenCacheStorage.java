package com.xjjlearning.springframework.security.jwt;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * The type Jwt token cache storage.
 *
 * @author Dax
 * @since 13 :28  2018/9/21
 */
public class JwtTokenCacheStorage implements JwtTokenStorage {
    /**
     * 查看缓存配置文件 ehcache.xml 定义 过期时间与 refresh token 过期一致.
     */
    private static final String TOKEN_CACHE = "usrTkn";


    @CachePut(value = TOKEN_CACHE, key = "#userId")
    @Override
    public JwtTokenPair put(JwtTokenPair jwtTokenPair, String userId) {
        return jwtTokenPair;
    }

    @CacheEvict(value = TOKEN_CACHE, key = "#userId")
    @Override
    public void expire(String userId) {
//        EhcacheHelper.remove(TOKEN_CACHE, uid);
    }


    @Cacheable(value = TOKEN_CACHE, key = "#userId")
    @Override
    public JwtTokenPair get(String userId) {
        return null;
    }
}
