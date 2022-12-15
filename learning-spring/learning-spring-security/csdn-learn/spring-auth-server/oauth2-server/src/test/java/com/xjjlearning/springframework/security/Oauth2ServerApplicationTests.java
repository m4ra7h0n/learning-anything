package com.xjjlearning.springframework.security;

import com.nimbusds.jose.jwk.RSAKey;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.security.KeyStore;

@SpringBootTest
class Oauth2ServerApplicationTests {

    @Test
    void contextLoads() throws Exception{
        KeyStore jks = KeyStore.getInstance("jks");
        String alias = "jose";
        String storePass = "xjjjjj";
        char[] pin = storePass.toCharArray();
        // 加载密钥
        jks.load(new ClassPathResource("jose.jks").getInputStream(), pin);

        RSAKey priJwk = RSAKey.load(jks, alias, pin);
        System.out.println("private key: " + priJwk.toJSONObject());
        RSAKey pubJwk = priJwk.toPublicJWK();
        System.out.println("public key: " + pubJwk.toJSONObject());
    }

}
