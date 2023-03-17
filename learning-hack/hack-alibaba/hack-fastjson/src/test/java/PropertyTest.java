import com.alibaba.fastjson.JSON;

import java.util.Properties;

/**
 * Created by xjj on 2023/3/9.
 */
public class PropertyTest {
    private String dataSource;

    public static void main(String[] args) {
        String poc = "{\"@type\":\"PropertyTest\",\"properties\":{\"dataSource\":\"kkk\"}}";
        JSON.parseObject(poc);
    }

    public void setProperties(Properties properties) {
        System.out.println("setProperties"); // yes
    }

    public void setDataSource(String dataSource) {
        System.out.println("setDataSource"); // no
    }

    public String getDataSource() {
        System.out.println("getDataSource"); // yes
        return dataSource;
    }
}
