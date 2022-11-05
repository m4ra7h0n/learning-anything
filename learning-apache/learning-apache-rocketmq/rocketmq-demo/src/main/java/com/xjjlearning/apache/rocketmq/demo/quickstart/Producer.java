package com.xjjlearning.apache.rocketmq.demo.quickstart;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class Producer {
    public static final int MESSAGE_COUNT = 1000;
    public static final String PRODUCER_GROUP = "XJJ_GROUP";
    public static final String DEFAULT_NAMESRVADDR = "127.0.0.1:9876";
    public static final String TOPIC = "TopicTest";
    public static final String TAG = "TagA";

    public static void main(String[] args) throws MQClientException, InterruptedException{
        DefaultMQProducer producer = new DefaultMQProducer(PRODUCER_GROUP);
        producer.setNamesrvAddr(DEFAULT_NAMESRVADDR);
        producer.start();
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            try {
                Message message = new Message(TOPIC, TAG, ("Hello RocketMq !").getBytes(RemotingHelper.DEFAULT_CHARSET));
//                message.setKeys();
                SendResult sendResult = producer.send(message);
                System.out.printf("%s%n", sendResult);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }
        producer.shutdown();
    }
}
