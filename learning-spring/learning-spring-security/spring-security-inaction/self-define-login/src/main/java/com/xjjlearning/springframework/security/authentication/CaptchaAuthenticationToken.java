package com.xjjlearning.springframework.security.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * 验证码认证凭据
 */
public class CaptchaAuthenticationToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	private final Object principal;

	private Object credentials;

    private String captcha;


	public CaptchaAuthenticationToken(Object principal, String captcha) {
		super(null);
		this.principal = principal;
        this.captcha = captcha;
		setAuthenticated(false);
	}


	public CaptchaAuthenticationToken(Object principal, String captcha,
                                      Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
        this.captcha = captcha;
		super.setAuthenticated(true); // must use super, as we override
	}

	@Override
	public String getCredentials() {
		return this.captcha;
	}

	@Override
	public Object getPrincipal() {
		return this.principal;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		Assert.isTrue(!isAuthenticated,
				"Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
		super.setAuthenticated(false);
	}

	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
		this.credentials = null;
	}

}
