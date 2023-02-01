# 包结构
```text
asm是学习asm的部分
javassist是学习javassist的部分
classfile是字节码文件学习的部分
deepjvm3是《深入理解jvm3》
deeptoeasy是《深入浅出jvm字节码》
```

# 文章
```text
字节码增强技术: https://tech.meituan.com/2019/09/05/java-bytecode-enhancement.html
```

# 工具
1.jinfo
```bash
卡着程序执行
ps -ef | grep StackOve  # get 30697
jinfo -flag ThreadStackSize 30697 # result -> -XX:ThreadStackSize=1024
```