《java8实战》
代码放在util/stream里了  
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

# 第二章
1.行为参数化(把行为抽象, 作为函数的参数传递)  
谓词: Predicate  
2.函数式接口: 只定义一个抽象方法的接口  
```java
public interface Comparator<T> {
    int compare(T o1, T o2);
}
public interface Runnable{
    void run();
}
public interface ActionListener extends EventListener{
    void actionPerformed(ActionEvent e);
}
public interface Callable<V>{
    V call();
}
public interface PrivilegedAction<V>{
    V run();
}
```
用函数式接口可以干什么呢？Lambda表达式允许你直接以内联的形式为函数式接口的抽象方法提供实现, 并把整个表达式作为函数式接口的实例（具体说来，是函数式接口一个具体实现的实例）.
```java
//Runnable r = () -> System.out.println("hello");
```
3.环绕执行(execute around)
固定模版并围绕某个核心的变更代码执行  
4.装箱(boxing)/拆箱(unboxing)

```java
import java.util.ArrayList;
import java.util.function.IntFunction;

class Boxing {
    public static void main(String[] args) {
        // int被包装成Integer, 消耗资源
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            list.add(i);
        }
        // 避免装箱
        IntPredicate evenNumbers = (int i) -> (i & 1) == 0;
        evenNumbers.test(1000);
        
        // 装箱
        Predicate<Integer> oddNumbers = (Integer i) -> i % 2 == 1;
        oddNumbers.test(1000);
    }
    
    @FunctionalInterface
    public interface IntPredicate{
        boolean test(int t);
    }
}
```
方法引用:  
Apple::getWeight  