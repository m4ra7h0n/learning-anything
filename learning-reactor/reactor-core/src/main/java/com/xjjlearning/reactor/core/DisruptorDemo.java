package com.xjjlearning.reactor.core;
//
// // https://tech.meituan.com/2016/11/18/disruptor.html
// public class DisruptorDemo {
//     public static void main(String[] args) {
//         int ringBufferCapacity = 8;
//         //消息事件生产Factory
//         LongEventFactory longEventFactory = new LongEventFactory();
//         //执行事件处理器线程Factory
//         ConsumerThreadFactory consumerThreadFactory = new ConsumerThreadFactory();
//         //用于环形缓冲区的等待策略。
//         WaitStrategy waitStrategy = new BlockingWaitStrategy();
//         //构建disruptor
//         Disruptor<LongEvent> disruptor = new Disruptor<>(
//                 longEventFactory,
//                 ringBufferCapacity,
//                 (Executor) consumerThreadFactory,
//                 ProducerType.SINGLE,
//                 waitStrategy);
//     }
// }
//
// class ConsumerThreadFactory implements ThreadFactory {
//     private final AtomicInteger index = new AtomicInteger(1);
//
//     @Override
//     public Thread newThread(Runnable r) {
//         return new Thread(r, "disruptor-thread-" + index.getAndIncrement());
//     }
// }
//
// class LongEventFactory implements EventFactory<LongEvent> {
//     @Override
//     public LongEvent newInstance() {
//         return new LongEvent();
//     }
// }
//
// class LongEvent {
//     private long value;
//
//     public void set(long value) {
//         this.value = value;
//     }
//
//     public long getValue() {
//         return value;
//     }
// }