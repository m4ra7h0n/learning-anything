package com.xjjlearning.apache.rocketmq.filter;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;

public class SqlFilterConsumer {
    public static void main(String[] args) throws Exception{
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("XJJ_GROUP");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.subscribe("SqlFilterTest",
                MessageSelector.bySql("(TAGS is not null and TAGS in ('TagA', 'TagB'))" +
                        "and (a is not null and a between 0 and 3)"));
//        consumer.subscribe("SqlFilterTest", "TagA || TagB");

        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            msgs.forEach(
                    msg -> {
                        try {
                            System.out.println(new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
            );
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
        System.out.printf("Consumer Started.%n");
    }
}
