package com.xjjlearning.springframework.security.service.impl;

import com.xjjlearning.springframework.security.service.CaptchaCacheStorage;
import com.xjjlearning.springframework.security.service.CaptchaService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * created by xjj on 2022/12/11
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {

    public CaptchaServiceImpl(CaptchaCacheStorage captchaCacheStorage) {
        this.captchaCacheStorage = captchaCacheStorage;
    }

    private CaptchaCacheStorage captchaCacheStorage;

    @Override
    public boolean sendCaptcha(String phone) {
        String captchaExisted = captchaCacheStorage.get(phone);
        // 节约成本的话如果缓存中有当前手机可用的验证码 不再发新的验证码
        if (StringUtils.isNotEmpty(captchaExisted)) {
            return true;
        }
        // 生成验证码并放入缓存
        String captcha = captchaCacheStorage.put(phone);
        // TODO 调用第三方发送验证码
        return true;
    }

    @Override
    public boolean verifyCaptcha(String phone, String captcha) {
        String captchaExisted = captchaCacheStorage.get(phone);
        if (Objects.equals(phone, captchaExisted)) {
            // 验证通过 手动过期验证码
            captchaCacheStorage.expire(phone);
            return true;
        }
        return false;
    }
}
