《java8实战》 前三章代码放在util/function/test里了  
四五六章代码放在util/stream/test里了  
我先看的stream源码 再来看的这个pdf教程 多少感觉有点墨迹

# 第一章 (function)

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

public interface Runnable {
    void run();
}

public interface ActionListener extends EventListener {
    void actionPerformed(ActionEvent e);
}

public interface Callable<V> {
    V call();
}

public interface PrivilegedAction<V> {
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
    public interface IntPredicate {
        boolean test(int t);
    }
}
```

# 第三章

方法引用:  
Apple::getWeight

# 第四章(stream)

流到底是什么呢？简短的定义就是"从支持数据处理操作的源生成的元素序列"  
建造者模式(builder pattern)

**循环合并**

```java
// filter(Dish::getCalories).map(Dish::getName)  

// equals to 

// for (int i = 0; i < n; i++) {
//     Dish[i] = filter(Dish[i]);
//     Dish[i] = map(Dish[i])
// }
```

# 第五章

筛选/切片 filter,distinct,skip,limit  
提取/转换/映射 map,flatMap   
截断 limit  
查找/匹配 findFirst,findAny,allMatch,noneMatch,anyMatch   
归约/迭代合并 reduce  
reduce 反复结合每个元素，直到流被归约成一个值  
reduce,sorted,distinct是有状态的需要保存整个值

# 第六章

collect, Collector, Collection  
java.util.stream.Collectors  
java.util.stream.Collector  
java.util.stream.Stream$collect()

reduce方法旨在把两个值结合起来生成一个新值，它是一个不可变的归约。  
与此相反，collect方法的设计就是要改变容器，从而累积要输出的结果

```java
class Test {
    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
        List<Integer> numbers = stream.reduce(
                // 滥用1
                new ArrayList<Integer>(),
                (List<Integer> l, Integer e) -> {
                    l.add(e);
                    return l;
                }

                // 滥用2
                // (List<Integer> l1, List<Integer> l2) -> {
                //     l1.addAll(l2);
                //     return l1; 
                // }
        );
    }
}
```

# 7章

## 用一个例子 累加自然数来比较各种迭代器的性能

Stream.iterator().limit().reduce() / parallel()  
LongStream.rangeClosed().reduce() / parallel()  -> best  
forkJoin()  
正常累加

## 学习使用 forkJoin

分治(Divide and conquer)

and Performance optimization

# 8章

学习Lambda在设计模式中的应用  
learn the application of Lambda to design pattern  
策略模式(strategy)  
模版方法模式(template method)  
工厂模式(factory)  
观察者模式(observer)

# 9章

learn default method in interface

# 10章

lean Optional class

# 11章

learn CompletableFuture  
thenAccept(): 哪个future先完成先执行传入的方法  
thenApply(): (相当于map)  
thenCompose(): (相当于flatMap)  
thenCombine(): 并行执行两个future后, 使用二者的结果执行传入的方法  
CompletableFuture.supplyAsync(): 执行任务async  

# 12章

