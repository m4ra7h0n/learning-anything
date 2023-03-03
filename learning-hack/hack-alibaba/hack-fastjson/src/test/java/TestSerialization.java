import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class TestSerialization {
    /**
     * private -> 只序列化有get方法的, 且执行get方法
     * public -> 都序列化, 并执行get方法
     */
    public static void main(String[] args) {
        // 非自省
        User a = new User();
        System.out.println("===========================");
        String jsonstr_a = JSON.toJSONString(a);
        System.out.println(jsonstr_a);

        // 自省, 即通过传入的文本判断相应的对象
        User b = new User();
        System.out.println("===========================");
        String jsonstr_b = JSON.toJSONString(b, SerializerFeature.WriteClassName);
        System.out.println(jsonstr_b);
    }
}