package com.xjjlearning.maven.accountcaptcha;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AccountCaptchaApplicationTests {
    @Before
    void before(){
        System.out.println("before test..");
    }

    @Test
    void contextLoads() {
        System.out.println("begin test..");
    }

}
