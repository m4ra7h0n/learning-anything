package com.xjjlearning.apache.rocketmq.streams.source;

import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.streams.client.StreamBuilder;
import org.apache.rocketmq.streams.client.source.DataStreamSource;
import org.apache.rocketmq.streams.client.transform.DataStream;

public class FileSourceExample {
    public static void main(String[] args) {
        DataStreamSource source = StreamBuilder.dataStream("namespace", "pipeline");
        try {
            Thread.sleep(1000 * 3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("begin streams code.");
        DataStream dataStream = source.fromFile("scores.txt", true)
                .map(message -> message)
                .filter(message -> ((JSONObject) message).getInteger("score") > 90)
                .selectFields("name", "subject")
                .toPrint();
        dataStream.start();
    }
}
