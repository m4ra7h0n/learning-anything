学习方式: 官网example + 基础架构文章 + 哪里不会搜哪里 + 偶尔看看官网  

JWS：Signed JWT签名过的jwt
JWE：Encrypted JWT部分payload经过加密的jwt；目前加密payload的操作不是很普及；
JWK：JWT的密钥，也就是我们常说的 scret；
JWKset：JWT key set在非对称加密中，需要的是密钥对而非单独的密钥，在后文中会阐释；
JWA：当前JWT所用到的密码学算法；
nonsecure JWT：当头部的签名算法被设定为none的时候，该JWT是不安全的；因为签名的部分空缺，所有人都可以修改。

header:
iss  【issuer】发布者的url地址
sub 【subject】该JWT所面向的用户，用于处理特定应用，不是常用的字段
aud 【audience】接受者的url地址
exp 【expiration】 该jwt销毁的时间；unix时间戳
nbf  【not before】 该jwt的使用时间不能早于该时间；unix时间戳
iat   【issued at】 该jwt的发布时间；unix 时间戳
jti    【JWT ID】 该jwt的唯一ID编号