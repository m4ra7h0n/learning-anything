import java.util.Arrays;
import java.util.List;

/**
 * Created by xjj on 2023/3/8.
 */
public class TypeParameters {
    public static void main(String[] args) {
        Arrays.stream(List.class.getTypeParameters()).forEach(System.out::println);
    }
}
