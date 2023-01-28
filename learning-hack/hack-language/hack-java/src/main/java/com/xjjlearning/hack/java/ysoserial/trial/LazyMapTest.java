package com.xjjlearning.hack.java.ysoserial.trial;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.map.LazyMap;

import java.util.HashMap;
import java.util.Map;

/**
 * created by xjj on 2023/1/28
 */
public class LazyMapTest {
    public static void main(String[] args) {
        // lazyMap只重写了get方法
        // 其中我们封装的map使用了transient修饰 在序列化的时候无法传输
        Map lazymap = LazyMap.decorate(new HashMap(), new Transformer() {
            @Override
            public Object transform(Object input) {
                return (Integer) input + 1;
            }
        });
        lazymap.put(2, 3);
        System.out.println(lazymap.get(2));
        System.out.println(lazymap.get(1));
    }
}
