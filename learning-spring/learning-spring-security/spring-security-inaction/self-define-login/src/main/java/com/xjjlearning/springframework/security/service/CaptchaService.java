package com.xjjlearning.springframework.security.service;

/**
 * created by xjj on 2022/12/11
 */
public interface CaptchaService {

    boolean sendCaptcha(String phone);

    boolean verifyCaptcha(String phone, String captcha);

}
