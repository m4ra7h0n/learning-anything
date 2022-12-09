学习方式: 官网example + 基础架构文章 + 哪里不会搜哪里 + 偶尔看看官网
官网sample是坨屎   
看这个:  
https://gitee.com/felord/spring-security-oauth2-tutorial  

Authorization头代表已经认证完毕 带有该头的请求无论是basic或是csrfToken都可以通过

```text

JWS：Signed JWT签名过的jwt
JWE：Encrypted JWT部分payload经过加密的jwt；目前加密payload的操作不是很普及；
JWK：JWT的密钥，也就是我们常说的 scret；
JWKset：JWT key set在非对称加密中，需要的是密钥对而非单独的密钥，在后文中会阐释；
JWA：当前JWT所用到的密码学算法；
nonsecure JWT：当头部的签名算法被设定为none的时候，该JWT是不安全的；因为签名的部分空缺，所有人都可以修改。

```

header:

```text

iss  【issuer】发布者的url地址
sub 【subject】该JWT所面向的用户，用于处理特定应用，不是常用的字段
aud 【audience】接受者的url地址
exp 【expiration】 该jwt销毁的时间；unix时间戳
nbf  【not before】 该jwt的使用时间不能早于该时间；unix时间戳
iat   【issued at】 该jwt的发布时间；unix 时间戳
jti    【JWT ID】 该jwt的唯一ID编号

```


authorization grant type:

```text
授权码模式（authorization code）：功能最完整、流程最严密的授权模式。特点是通过第三方应用的后台服务器，与服务提供平台的认证服务器进行互动获取资源。
简化模式（implicit）：不通过第三方应用服务器，直接在浏览器中向认证服务器申请token令牌，跳过了授权码这个步骤。所有步骤在浏览器中完成，token对用户可见，且第三方应用不需要认证。
密码模式（resource owner password credentials）：用户向第三方应用提供自己的用户名和密码。第三方应用使用这些信息，向服务提供平台索要授权。在这种模式中，用户必须把自己的密码给第三方应用，但是第三方应用不得储存密码。这通常用在用户对第三方应用高度信任的情况下，比如第三方应用是操作系统的一部分，或者由一个著名公司出品。而认证服务器只有在其他授权模式无法执行的情况下，才能考虑使用这种模式。
客户端模式（client credentials）：指第三方应用以自己的名义，而不是以用户的名义，向服务提供平台进行认证。严格地说，客户端模式并不属于OAuth框架所要解决的问题。在这种模式中，用户直接向第三方应用注册，第三方应用以自己的名义要求服务提供平台提供服务，其实不存在授权问题。
```

filter排序: class HttpSecurity  
```text
	@Override
	protected DefaultSecurityFilterChain performBuild() {
		this.filters.sort(OrderComparator.INSTANCE);
		List<Filter> sortedFilters = new ArrayList<>(this.filters.size());
		for (Filter filter : this.filters) {
			sortedFilters.add(((OrderedFilter) filter).filter);
		}
		return new DefaultSecurityFilterChain(this.requestMatcher, sortedFilters);
	}
```