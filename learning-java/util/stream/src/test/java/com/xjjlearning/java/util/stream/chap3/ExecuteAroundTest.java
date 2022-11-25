package com.xjjlearning.java.util.stream.chap3;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@SpringBootTest
public class ExecuteAroundTest {
    @Test
    public void executeAroundTest() throws IOException {
        String s = processFile(new BufferedReaderProcessor() {
            @Override
            public String process(BufferedReader bufferedReader) throws IOException {
                return bufferedReader.readLine();
            }
        });

        processFile(BufferedReader::readLine);

        processFile(reader -> reader.readLine() + reader.readLine()); // 传入BufferedReader 返回String

        processFile(reader -> {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                sb.append(reader.readLine());
            }
            return sb.toString();
        });
    }
    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("src/test/java/com/xjjlearning/java/util/stream/chap3/data.txt"))) {
            return p.process(br); //抽象出去
        }
    }

    // 行为本身
    @FunctionalInterface
    public interface BufferedReaderProcessor {
        String process(BufferedReader bufferedReader) throws IOException; //传入BufferedReader 返回String
    }
}
