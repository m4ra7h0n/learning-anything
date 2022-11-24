《java8实战》
# 第一章
命令式编程 --> 函数式编程  
1.流处理stream(4-7)  
2.Stream API就是构建在通过传递代码使操作行为实现参数化的思想上的(2,3,13,14)  
string.stream().sort(String::length)  
3.stream可以替代thread, parallelstream(7,13), 但是需要数据之间无互动, 这是一个限制, 否则只能用completablefuture或者响应式编程  
4.默认方法 List中不能添加stream中的sort方法 则使用默认方法  
java8后接口如今可以包含实现类没有提供实现的方法签名, 那谁来实现它呢？缺失的方法主体随接口提供 Java 8在接口声明中使用新的default关键字来表示这一点  
```java
//接口A可以有默认实现 实现类可以不重写使用默认实现 或者重写 或者重写并将其声明为abstract  
interface A { 
    default void def() {
        System.out.println("default method");
    }
}
```
Collection主要是为了存储和访问数据，而Stream则主要用于描述对数据的计算  
