import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by xjj on 2023/7/14.
 */
public class ClassTest {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
//        Class evil = Evil.class.getClass();
//        Constructor con = evil.getDeclaredConstructor();

        Class.forName("Evil");
    }
}
