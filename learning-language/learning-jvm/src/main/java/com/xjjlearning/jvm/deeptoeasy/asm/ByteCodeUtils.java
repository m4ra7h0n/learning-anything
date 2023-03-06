package com.xjjlearning.jvm.deeptoeasy.asm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by xjj on 2023/3/6.
 */
public class ByteCodeUtils {
    public static void savaToFile(String className, byte[] byteCode) throws IOException {
        String pathName = "/Volumes/ONETSSD/IdeaProjects/learning-anything/learning-language/learning-jvm/target/classes/"
                + className.replace(".", "/");

        File file = new File(pathName + ".class");
        if ((!file.exists() || file.delete()) && file.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(byteCode);
            }
        }
    }
}
