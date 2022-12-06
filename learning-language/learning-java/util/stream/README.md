stream articles:  
https://juejin.cn/post/7100824118831874078  
https://juejin.cn/post/7101217470542774308  
https://juejin.cn/post/7101873469456908296  
https://juejin.cn/post/7103718952626290695  
https://juejin.cn/post/7105949477776654344  

stream examples:  
https://developer.aliyun.com/article/903870  

# 术语
sink: 控制流传播的每一个流的节点, 包含begin, end方法, accept方法用于向下游传播    

```java
public class Source {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("kotlin", "java", "go", "Scala", "Haskell");
        names.stream()
                .map(String::length)
                .filter(length -> length <= 4)
                .forEach(w -> System.out.println("language -> " + w));
        

    }
}
```
# overall process
## 1.the chain (map, filter)    
stream() method return Head as the head of the chain which is extended from ReferencePipeline, and the same as StatelessOp, StatefulOp  
ReferencePipeline.Head --nextStage--> StatelessOp(map) --nextStage--> StatelessOp(filter)  
 
## 2.TerminalOp -> forEach  
ForEachOp.OfRef: class -> evaluateSequential() -> wrapAndCopyInto() //wrap firstly then copyInto  
traverse previousStage from the last Stage 'filter' and wrap it as the downstream  
 
**1.wrap:**  
filter: opWrapSink -> return Sink.ChainedReference: class  
map: opWrapSink -> return Sink.ChainedReference: class  
the result:   
`Sink.ChainedReference(map) -> Sink.ChainedReferen(filter) -> ceForEachOp.OfRef`  

**2.copyInto**  
// call begin() method from map sink to OfRef sink ordered  
`wrappedSink.begin(spliterator.getExactSizeIfKnown());`  
// for each remaining method, call their accept method (Sink.ChainedReference)    
`spliterator.forEachRemaining(wrappedSink);`  
// call end() method from map sink to OfRef sink ordered  
`wrappedSink.end();`  

```java
// Pass elements through the stages of the flow pipeline  
interface Sink<T> extends Consumer<T> {

    //发送数据到sink之前调用的方法
    default void begin(long size) {
        // downstream.begin()  
    } 

    //在所有数据被发送到sink后调用的方法
    default void end() {}

    //用于判断是否短路
    default boolean cancellationRequested() {
        return false;
    }
}
class Method {
    public static void main(String[] args) {
        // what's wrap ? that's returning inner element Sink.ChainedReference
        return new StatelessOp<P_OUT, P_OUT>(this, StreamShape.REFERENCE,
                StreamOpFlag.NOT_SIZED) {
            @Override
            Sink<P_OUT> opWrapSink(int flags, Sink<P_OUT> sink) {
                return new Sink.ChainedReference<P_OUT, P_OUT>(sink) {
                    @Override
                    public void begin(long size) {
                        downstream.begin(-1);
                    }

                    @Override
                    public void accept(P_OUT u) {
                        if (predicate.test(u)) //filter -> predicate.test() and map -> function.apply()
                            downstream.accept(u); // downstream
                    }
                };
            }
        };
    }
}
```

# spliterator
stream use ArraySpliterator by default which is a sequential spliterator  

# stateful of stateless
filter, map, flatMap, peek, unordered .etc methods are stateless  
distinct, sorted, limit, skip methods .etc are stateful  
```java
abstract static class StatefulOp<E_IN, E_OUT>
        extends ReferencePipeline<E_IN, E_OUT> {

    StatefulOp(AbstractPipeline<?, E_IN, ?> upstream,
               StreamShape inputShape,
               int opFlags) {
        super(upstream, opFlags);
        assert upstream.getOutputShape() == inputShape;
    }

    @Override
    final boolean opIsStateful() { // notice, only this one is different
        return true;
    }

    @Override
    abstract <P_IN> Node<E_OUT> opEvaluateParallel(PipelineHelper<E_OUT> helper,
                                                   Spliterator<P_IN> spliterator,
                                                   IntFunction<E_OUT[]> generator);
}
```
such as distinct(), Execute chain in sequence(map, filter, distinct, forEach), and when executing to distinct, if seen hashmap contains the value before, then stop the sink from spreading downstream(unordered)  
such as sort(), collect the elements into an array in forEachRemaining method. and sorted in end method, then downstream the elements ordered in end method.

# TerminalOp
## Non-short circuit operation
**ForEachOp:** forEach(), forEachOrdered()   
**ReduceOp:** reduce(), max(), min(), collect()    

such as:  
reduce() equals to Flux.generate(0L, (state, sink) -> ...)  

collection() -> reduce  
## short circuit operation
**MatchOp:**  
anyMatch() -> sink.cancellationRequested() && spliterator.tryAdvance(sink) //先判断是否停下然后单步执行sink
// Determine whether to stop and then step through
**FindOp:**  
...

# Collectors
collect() = ReduceOp + Collector

# conclusion
Spliterator
Collectors