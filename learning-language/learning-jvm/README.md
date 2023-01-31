# 工具
1.jinfo
```bash
卡着程序执行
ps -ef | grep StackOve  # get 30697
jinfo -flag ThreadStackSize 30697 # result -> -XX:ThreadStackSize=1024
```