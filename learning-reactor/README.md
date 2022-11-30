官方中文文档: https://htmlpreview.github.io/?https://github.com/get-set/reactor-core/blob/master-zh/src/docs/index.html#about-doc  

在响应式编程方面，微软跨出了第一步，它在 .NET 生态中创建了响应式扩展库（Reactive Extensions library, Rx）。接着 RxJava 在JVM上实现了响应式编程。后来，在 JVM 平台出现了一套标准的响应式 编程规范，它定义了一系列标准接口和交互规范。并整合到 Java 9 中（使用 Flow 类）。  

needs jdk-9  
download jdk-9: https://www.oracle.com/java/technologies/javase/javase9-archive-downloads.html  
1.idea -> project structure -> modules   
-> sources -> 9  
-> dependencies -> 1.9  
2.maven -> properties -> <java.version>1.9</java.version>  
3.preference -> build,execution,deployment -> Compiler -> Java Compiler -> module -> 9  