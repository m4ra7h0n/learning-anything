package com.xjj.learning.maven.accountweb;
import com.xjj.learning.maven.accountcaptcha.captchaMethod;

public class webMethod {
    public void testCaptchaMethod(){
        System.out.println("web Method");
        System.out.println(new captchaMethod().returnCaptcha());
    }
}
