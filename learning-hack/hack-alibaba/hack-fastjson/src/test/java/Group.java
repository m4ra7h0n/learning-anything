import java.util.Arrays;

/**
 * Created by xjj on 2023/3/7.
 */
public class Group {
    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + Arrays.toString(users) +
                '}';
    }

    public Integer id = 1;
    public String name = "xjj";
    public User[] users = new User[2];
}
