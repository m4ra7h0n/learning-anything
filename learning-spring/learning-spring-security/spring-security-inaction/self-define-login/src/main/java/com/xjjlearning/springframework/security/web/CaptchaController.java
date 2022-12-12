package com.xjjlearning.springframework.security.web;

import com.xjjlearning.springframework.security.entity.Rest;
import com.xjjlearning.springframework.security.entity.RestBody;
import com.xjjlearning.springframework.security.service.CaptchaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * created by xjj on 2022/12/11
 */
@RestController
@RequestMapping("/captcha")
public class CaptchaController {
    @Resource
    CaptchaService captchaService;

    /**
     * 模拟向手机号发送验证码
     * @param phone the mobile
     * @return Rest<?>
     */
    @GetMapping("/{phone}")
    public Rest<?> captchaByMobile(@PathVariable String phone) {
        // TODO 验证手机号合法性
        if (captchaService.sendCaptcha(phone)) {
            return RestBody.ok();
        }
        return RestBody.failure(-999, "发送失败");
    }

    public void test() {}
}
