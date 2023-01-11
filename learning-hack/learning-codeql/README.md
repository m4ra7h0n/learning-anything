# 说明
1.这个模块是使用vscode写的 因为vscode里面有插件, 可以运行codeql  
2.这里面还差一个codeql-cli 因为文件2G所以放在本地了 需要配置classpath seebug里面有教程跟着来就可以  
3.还有一个vscode-codql-starter/ql下需要放codeql的官方库, 整个下载放里面: https://github.com/github/codeql  
4.src下面放你需要测试的项目

# 学习方式
官方资源: https://codeql.github.com/docs/
大佬搜集资源: https://github.com/ASTTeam/CodeQL  

学习顺序: 
https://paper.seebug.org/1078 (初始搭建环境 简单demo)   
https://www.4hou.com/posts/o6wX (具体教程)    

官方ql: https://github.com/semmle/ql  

# 生成数据库方式
在src文件夹下的每个项目下执行
```bash
codeql database create name-database --language=xxx
```
language 为 java/python/javascript/等
