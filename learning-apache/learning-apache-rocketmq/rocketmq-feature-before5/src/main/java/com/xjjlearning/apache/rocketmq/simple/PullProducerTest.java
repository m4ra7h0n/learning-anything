package com.xjjlearning.apache.rocketmq.simple;

import org.apache.rocketmq.client.QueryResult;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class PullProducerTest {
    public static void main(String[] args) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("XJJ_GROUP");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();

        for (int i = 0; i < 1; i++)
            try {
                Message msg = new Message("TopicPullTest",
                        "TagA",
                        "key113",
                        "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult sendResult = producer.send(msg);
                System.out.printf("%s%n", sendResult);

                QueryResult queryMessage =
                        producer.queryMessage("TopicPullTest", "key113", 10, 0, System.currentTimeMillis());
                for (MessageExt m : queryMessage.getMessageList()) {
                    System.out.printf("%s%n", m);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        producer.shutdown();
    }
}
