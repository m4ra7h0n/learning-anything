安全中加载类主要使用TemplatesImpl和Bcel

loadClass(): 从已加载的类缓存，父加载器寻找类(双亲委派)
findClass(): 指定URL类，从本地文件系统，jar包，远程网络加载
defineClass(): native方法，将字节码转换为java类

# FileClassLoader
自定义文件类加载器
# HelloClassLoader
从远端服务器加载类的demo
# HelloDefineClass
使用反射调用defineClass加载字节码生成java类
# HelloTemplatesImpl
使用TemplatesImpl加载恶意字节码demo

# JAXP
https://zh.wikipedia.org/wiki/JAXP
