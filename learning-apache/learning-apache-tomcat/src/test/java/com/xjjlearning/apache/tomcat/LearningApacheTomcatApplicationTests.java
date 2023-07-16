package com.xjjlearning.apache.tomcat;

import com.xjjlearning.apache.tomcat.ex02.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@SpringBootTest
class LearningApacheTomcatApplicationTests {


    @Test
    void contextLoads() {
        try {
            File classPath = new File(Constants.WEB_ROOT);
            String file = new URL("file", null, classPath.getCanonicalPath() + File.separator).toString();
            System.out.println(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
