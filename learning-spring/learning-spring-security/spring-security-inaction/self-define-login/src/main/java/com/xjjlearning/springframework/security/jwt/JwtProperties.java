package com.xjjlearning.springframework.security.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Jwt 在 springboot application.yml 中的配置文件
 *
 * @author Felordcn
 * @since 15 :06 2019/10/25
 */
@Data
@ConfigurationProperties(prefix= JwtProperties.JWT_PREFIX)
public class JwtProperties {
    static final String JWT_PREFIX= "jwt.config";
    /**
     * 是否可用
     */
    private boolean enabled;
    /**
     * jks 路径
     */
    private String keyLocation;
    /**
     * key alias
     */
    private String keyAlias;
    /**
     * key store pass
     */
    private String keyPass;
    /**
     * jwt签发者
     **/
    private String iss;
    /**
     * jwt所面向的用户
     **/
    private String sub;
    /**
     * access jwt token 有效天数
     */
    private int accessExpDays;
    /**
     * refresh jwt token 有效天数
     */
    private int refreshExpDays;
}
