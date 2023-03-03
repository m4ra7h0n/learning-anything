package com.xjjlearning.hack.java.ysoserial.trial;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

/**
 * created by xjj on 2023/2/11
 */
public class User implements Serializable {
    protected String name;
    protected User parent;

    public User(String name) {
        this.name = name;
    }


    public void setParent(User parent) {
        this.parent = parent;
    }

    public static void main(String[] args) throws IOException {
        User user = new User("Bob");
        user.setParent(new User("Josua"));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(user);
        System.out.println(Base64.getEncoder().encodeToString(out.toByteArray()));
        // rO0ABXNyAC5jb20ueGpqbGVhcm5pbmcuaGFjay5qYXZhLnlzb3NlcmlhbC50cmlhbC5Vc2VyDVMeEKgpVKECAAJMAARuYW1ldAASTGphdmEvbGFuZy9TdHJpbmc7TAAGcGFyZW50dAAwTGNvbS94ampsZWFybmluZy9oYWNrL2phdmEveXNvc2VyaWFsL3RyaWFsL1VzZXI7eHB0AANCb2JzcQB+AAB0AAVKb3N1YXA=
    }
    /**
     * xjj@xjj zkar % go run main.go dump -B rO0ABXNyAC5jb20ueGpqbGVhcm5pbmcuaGFjay5qYXZhLnlzb3NlcmlhbC50cmlhbC5Vc2VyDVMeEKgpVKECAAJMAARuYW1ldAASTGphdmEvbGFuZy9TdHJpbmc7TAAGcGFyZW50dAAwTGNvbS94ampsZWFybmluZy9oYWNrL2phdmEveXNvc2VyaWFsL3RyaWFsL1VzZXI7eHB0AANCb2JzcQB+AAB0AAVKb3N1YXA=
     * @Magic - 0xac ed
     * @Version - 0x00 05
     * @Contents
     *   TC_OBJECT - 0x73
     *     TC_CLASSDESC - 0x72
     *       @ClassName
     *         @Length - 46 - 0x00 2e
     *         @Value - com.xjjlearning.hack.java.ysoserial.trial.User - 0x63 6f 6d 2e 78 6a 6a 6c 65 61 72 6e 69 6e 67 2e 68 61 63 6b 2e 6a 61 76 61 2e 79 73 6f 73 65 72 69 61 6c 2e 74 72 69 61 6c 2e 55 73 65 72
     *       @SerialVersionUID - 960144202449638561 - 0x0d 53 1e 10 a8 29 54 a1
     *       @Handler - 8257536
     *       @ClassDescFlags - SC_SERIALIZABLE - 0x02
     *       @FieldCount - 2 - 0x00 02
     *       []Fields
     *         Index 0:
     *           Object - L - 0x4c
     *           @FieldName
     *             @Length - 4 - 0x00 04
     *             @Value - name - 0x6e 61 6d 65
     *           @ClassName
     *             TC_STRING - 0x74
     *               @Handler - 8257537
     *               @Length - 18 - 0x00 12
     *               @Value - Ljava/lang/String; - 0x4c 6a 61 76 61 2f 6c 61 6e 67 2f 53 74 72 69 6e 67 3b
     *         Index 1:
     *           Object - L - 0x4c
     *           @FieldName
     *             @Length - 6 - 0x00 06
     *             @Value - parent - 0x70 61 72 65 6e 74
     *           @ClassName
     *             TC_STRING - 0x74
     *               @Handler - 8257538
     *               @Length - 48 - 0x00 30
     *               @Value - Lcom/xjjlearning/hack/java/ysoserial/trial/User; - 0x4c 63 6f 6d 2f 78 6a 6a 6c 65 61 72 6e 69 6e 67 2f 68 61 63 6b 2f 6a 61 76 61 2f 79 73 6f 73 65 72 69 61 6c 2f 74 72 69 61 6c 2f 55 73 65 72 3b
     *       []ClassAnnotations
     *         TC_ENDBLOCKDATA - 0x78
     *       @SuperClassDesc
     *         TC_NULL - 0x70
     *     @Handler - 8257539
     *     []ClassData
     *       @ClassName - com.xjjlearning.hack.java.ysoserial.trial.User
     *         {}Attributes
     *           name
     *             TC_STRING - 0x74
     *               @Handler - 8257540
     *               @Length - 3 - 0x00 03
     *               @Value - Bob - 0x42 6f 62
     *           parent
     *             TC_OBJECT - 0x73
     *               TC_REFERENCE - 0x71
     *                 @Handler - 8257536 - 0x00 7e 00 00
     *               @Handler - 8257541
     *               []ClassData
     *                 @ClassName - com.xjjlearning.hack.java.ysoserial.trial.User
     *                   {}Attributes
     *                     name
     *                       TC_STRING - 0x74
     *                         @Handler - 8257542
     *                         @Length - 5 - 0x00 05
     *                         @Value - Josua - 0x4a 6f 73 75 61
     *                     parent
     *                       TC_NULL - 0x70
     *
     * xjj@xjj zkar %
     */
}
