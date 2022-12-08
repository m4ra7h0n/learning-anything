授权码模式流程  
官网: https://docs.spring.io/spring-security/reference/servlet/oauth2/client/authorization-grants.html  
启动authorization-server  
启动resource-server  
启动client

```text

访问8080(client)
9000拦截，访问:9000/.well-known/openid-configuration获取oauth2地址(连着发3个)
9000尝试get一下/oauth2/authorize发现没问题有响应
创建http session
8080尝试get一下 :8080/oauth2/authorization/messaging-client-oidc发现没有问题
9000 get /login 然后填表单post /login登录


跳转 9000 get :9000/oauth2/authorize?response_type=code&client_id=messaging-client&scope=openid%20profile&state=tb3twkS1F8uocUhLYv1YEaUVUhVBhNi9wWZs0tREA6M%3D&redirect_uri=http://127.0.0.1:8080/login/oauth2/code/messaging-client-oidc&nonce=c9NFo17Ezjrh6Gcvj1Osivj3wB9pt_fb0SpDrrEM_uI&continue'
点击按钮授权 9000 post /oauth2/authorize 生成授权码 code 并携带授权码访问回调地址

9000 get :8080/login/oauth2/code/messaging-client-oidc?code=wGmlwgn6H_ZruaiLrweu86WRl_wVyHU9qYi4Sp6nWj__nvlnVKD_BMiMPRvIzQlQDzrdz3jROaYvjdOKadnW9BmqaIt2vfQxKDxP_l9xTJHCU_t17mfRzIf8Bgz4Ty84&state=XQdLIflCsQKBwi97ylVO4yTQJ6cRzbafOOIz-4DzX-4%3D':

9000 post /oauth2/token 使用登录获取到的basic认证转换成jwt
9000 get /oauth2/jwks 获取key 然后生成jwt
9000 get /userinfo  使用jwt获取用户信息
9000 get :8080/?continue  9000通知客户端继续
8080 get :8080/index 访问主页

使用jwt get /userinfo获取用户信息
```
