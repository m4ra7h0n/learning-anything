package com.xjjlearning.apache.rocketmq.simple;

import org.apache.rocketmq.client.consumer.DefaultLitePullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageQueue;

public class PullConsumerTest {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("XJJ_GROUP");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.start();
        try {
            MessageQueue mq = new MessageQueue();
            mq.setQueueId(0);
            mq.setTopic("TopicPullTest");
            mq.setBrokerName("xjj.local");
            long offset = 26;

            long beginTime = System.currentTimeMillis();
            PullResult pullResult = consumer.pullBlockIfNotFound(mq, null, offset, 32);
            System.out.printf("%s%n", System.currentTimeMillis() - beginTime);
            System.out.printf("%s%n", pullResult.getMsgFoundList());
            System.out.printf("%s%n", pullResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
        consumer.shutdown();
    }
}
