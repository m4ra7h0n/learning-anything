package com.xjjlearning.apache.maven.accountweb;
import com.xjjlearning.apache.maven.accountcaptcha.captchaMethod;

public class webMethod {
    public void testCaptchaMethod(){
        System.out.println("web Method");
        System.out.println(new captchaMethod().returnCaptcha());
    }
}
