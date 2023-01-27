```bash
java -jar SerializationDumper-v1.13.jar -r serialization/person.ser
```



结果(https://xz.aliyun.com/t/8686): 
```text

STREAM_MAGIC - 0xac ed
STREAM_VERSION - 0x00 05
Contents
  TC_OBJECT - 0x73
  
    // class的描述
    
    TC_CLASSDESC - 0x72
      className
        Length - 46 - 0x00 2e
        Value - com.xjjlearning.hack.java.serialization.Person - 0x636f6d2e786a6a6c6561726e696e672e6861636b2e6a6176612e73657269616c697a6174696f6e2e506572736f6e
      serialVersionUID - 0x5b 3f 7e 54 f6 ef f8 1e
      
      // handle表示句柄的意思。对象序列流中每一个对象都有个句柄值。
      // 当一个对象第一次出现写入流中，会使用newHandle给这个对象分配一个句柄值。
      // 如果这个对象第二次写入了流中，只会写入之前该对象的句柄值。
      
      newHandle 0x00 7e 00 00
      classDescFlags - 0x03 - SC_WRITE_METHOD | SC_SERIALIZABLE
      fieldCount - 2 - 0x00 02
      Fields
        0:
          Int - I - 0x49
          fieldName
            Length - 3 - 0x00 03
            Value - age - 0x616765
        1:
          Object - L - 0x4c
          fieldName
            Length - 4 - 0x00 04
            Value - name - 0x6e616d65
          className1
            TC_STRING - 0x74
              newHandle 0x00 7e 00 01
              Length - 18 - 0x00 12
              Value - Ljava/lang/String; - 0x4c6a6176612f6c616e672f537472696e673b
              
      // 自定义的annotateClass方法写入的内容
      
      classAnnotations
        TC_ENDBLOCKDATA - 0x78
        
        
      // 如果被序列化对象的类，如果其父类没有实现Serializable接口，这个地方就是TC_NULL，表示空对象。
      // 如果其父类实现了实现了Serializable接口，那此处会写入其父类对应的ObjectStreamClass对象，即类描述对象。
      // 由于Person类没有父类，所以此处是TC_NULL。
      
      superClassDesc
        TC_NULL - 0x70
        
    // class的数据
    
    newHandle 0x00 7e 00 02
    classdata
      com.xjjlearning.hack.java.serialization.Person
        values
          age
            (int)22 - 0x00 00 00 16
          name
            (object)
              TC_STRING - 0x74
                newHandle 0x00 7e 00 03
                Length - 3 - 0x00 03
                Value - xjj - 0x786a6a
        
        // 自定义writeObject中我们自定义的输出字符串
        
        objectAnnotation
          TC_STRING - 0x74
            newHandle 0x00 7e 00 04
            Length - 16 - 0x00 10
            Value - This is a object - 0x546869732069732061206f626a656374
          TC_ENDBLOCKDATA - 0x78

```