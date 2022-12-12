package com.xjjlearning.springframework.security.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.xjjlearning.springframework.security.service.CaptchaCacheStorage;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * created by xjj on 2022/12/11
 */
@Service
public class CaptchaCacheStorageImpl implements CaptchaCacheStorage {

    private final static String SMS_CAPTCHA_CACHE = "captcha_cache";

    @CachePut(cacheNames = SMS_CAPTCHA_CACHE, key = "#phone")
    @Override
    public String put(String phone) {
        return RandomUtil.randomNumbers(5);
    }

    @Cacheable(cacheNames = SMS_CAPTCHA_CACHE, key = "#phone")
    @Override
    public String get(String phone) {
        return null;
    }

    @CacheEvict(cacheNames = SMS_CAPTCHA_CACHE, key = "#phone")
    @Override
    public void expire(String phone) {

    }
}
