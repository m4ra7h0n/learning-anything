import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Test;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TestDeSerialization {
    @Test
    public void selfExamination() {
        // 自省 使用@type
        // public -> 不管有没有setter都赋值, 执行字符串中变量的setter
        // private -> 只赋值有setter的变量, 执行相应setter

        // 自省情况下 在使用parseObject(jsonStr)时可以调用全部的getter, 原因是其调用toJSON方法
        // 其他方法在存在特殊字段的时候会执行getter方法
        // 特殊的getter: 没有setter方法 只有getter方法 且字段是Map/Collection/AtomicLong/AtomicInteger/AtomicBoolean的子类
        // 详见
        String jsonstr_b =
                "{" +
                        "\"@type\":\"User\",\n" +
                        "\"age1\":\"a1\",\n" +
                        "\"age2\":\"a2\",\n" +
                        "\"age3\":\"a3\",\n" +
                        "\"age4\":\"a4\",\n" +
                        "\"anything\":1,\n" +
                        "\"test\":1,\n" +
                        "\"name1\":\"rai4over1\",\n" +
                        "\"name2\":\"rai4over2\",\n" +
                        "\"name3\":\"rai4over3\",\n" +
                        "\"name4\":\"rai4over4\",\n" +
                        "\"prop1_1\":{\"@type\":\"java.util.Properties\",\"prop1_1\":\"1_1\"}," +
                        "\"prop1_2\":{\"@type\":\"java.util.Properties\",\"prop1_2\":\"1_2\"}," +
                        "\"prop1_3\":{\"@type\":\"java.util.Properties\",\"prop1_3\":\"1_3\"}," +
                        "\"prop1_4\":{\"@type\":\"java.util.Properties\",\"prop1_4\":\"1_4\"}," +
                        "\"prop2_1\":{\"@type\":\"java.util.Properties\",\"prop2_1\":\"2_1\"}," +
                        "\"prop2_2\":{\"@type\":\"java.util.Properties\",\"prop2_2\":\"2_2\"}" +
                        "}";
//        System.out.println("===========================");
//        JSONObject object = JSON.parseObject(jsonstr_b); // -> 可以调用所有getter 因为有toJSON()方法
//        System.out.println(object);
//        System.out.println("===========================");

//        // 没有setter的private使用如下即可
//        User e = JSON.parseObject(jsonstr_b, User.class, Feature.SupportNonPublicField); // -> 执行唯一的getter getProp2_2()
//        System.out.println(e);
//
//        System.out.println("===========================");
//        User c = (User) JSON.parse(jsonstr_b); // -> 执行唯一getter getProp2_2()
//        System.out.println(c);

        System.out.println("===========================");
        JSON.parseObject(jsonstr_b, User.class, Feature.SupportNonPublicField);
        System.out.println("===========================");
        JSON.parse(jsonstr_b, Feature.SupportNonPublicField);
        System.out.println("===========================");
        JSON.parseObject(jsonstr_b, Object.class, Feature.SupportNonPublicField);

//    parse(jsonStr) 构造方法+Json字符串指定属性的setter()+特殊的getter()
//    parseObject(jsonStr) 构造方法+Json字符串指定属性的setter()+所有getter() 包括不存在属性和私有属性的getter()
//    parseObject(jsonStr,Object.class) 构造方法+Json字符串指定属性的setter()+特殊的getter()
    }

    @Test
    public void setMethod() {
        // 测试
        // 会执行 setAnything() 方法
        String jsonstr_c = "{\"@type\":\"User\",\"anything\":false}";
        JSON.parseObject(jsonstr_c);
    }

    @Test
    public void testEvil() {
        String evil = "{\"@type\":\"Evil\",\"cmd\":\"ok\"}";
        JSON.parse(evil);
        System.out.println("----");
        JSON.parseObject(evil); //getter and setter 都调用了
        System.out.println("----");
        JSON.parseObject(evil, Object.class);
    }

    @Test
    public void f() {
        User user = new User();
        String s = JSON.toJSONString(user, SerializerFeature.PrettyFormat);
        System.out.println(s);
    }
    @Test
    public void notSelfExamination() {
        // 非自省, 需要传入反序列化成的类
        // public -> 有setter使用setter方法赋值, 没有使用反射(总之全部赋值)
        // private -> 调用setter方法赋值, 没有setter则为null

        // 非自省 无法执行全部getter 且不指定class的时候不执行任何方法
        String jsonstr_a = "" +
                "{\n" +
                "\"age1\":\"a1\",\n" +
                "\"age2\":\"a2\",\n" +
                "\"age3\":\"a3\",\n" +
                "\"age4\":\"a4\",\n" +
                "\"anything\":1,\n" +
                "\"name1\":\"rai4over1\",\n" +
                "\"name2\":\"rai4over2\",\n" +
                "\"name3\":\"rai4over3\",\n" +
                "\"name4\":\"rai4over4\",\n" +
                "\"prop1_1\":{\n\"prop1_1\":\"1_1\"\n},\n" +
                "\"prop1_2\":{\n\"prop1_2\":\"1_2\"\n},\n" +
                "\"prop1_3\":{\n\"prop1_3\":\"1_3\"\n},\n" +
                "\"prop1_4\":{\n\"prop1_4\":\"1_4\"\n},\n" +
                "\"prop2_1\":{\n\"prop2_1\":\"2_1\"\n},\n" +
                "\"prop2_2\":{\n\"prop2_2\":\"2_2\"\n}\n" +
                "}";
        System.out.println("===========================");
        // 详见 -> FieldDeserializer.setValue

        // 会执行setter
//        User b = JSON.parseObject(jsonstr_a, User.class);
//        System.out.println(b);

        // 不会执行任何方法
//        JSON.parseObject(jsonstr_a);
    }

}
