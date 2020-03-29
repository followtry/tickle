package cn.followtry.queue;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 练习并测试延迟队列
 *
 * @author jingzhongzhi
 * @since 2020/2/3
 */
public class DelayedQueueDemo {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DelayedQueueDemo.class);
    
    /**
     * main.
     */
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayedElement> delayQueue = new DelayQueue<>();
        System.out.println("开始");
        Producer producer = new Producer(delayQueue);
        Consumer consumer = new Consumer(delayQueue);
        Monitor monitor = new Monitor(delayQueue);
        
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);
        Thread monitorThread = new Thread(monitor);
        producerThread.start();
        monitorThread.start();
        
        Thread.sleep(10000);
        consumerThread.start();
        
        producerThread.join();
        consumerThread.join();
        monitorThread.join();
        System.out.println("结束");
    }
    
    private static class Producer implements Runnable {
        
        private final DelayQueue<DelayedElement> delayQueue;
        
        public Producer(DelayQueue<DelayedElement> delayQueue) {
            this.delayQueue = delayQueue;
        }
        
        @Override
        public void run() {
            int i = 0;
            while (true) {
                try {
                    ++i;
                    long expireTime = 100000L * i % 18000;
                    String data = "i = " + i;
                    LOGGER.info("我生产了一个 <==> ({}),过期时间为:{} ms后", data, expireTime);
                    delayQueue.offer(new DelayedElement(expireTime, data));
                    Thread.sleep(100);
                    if (i > 50) {
                        LOGGER.info("生产线程退出");
                        break;
                    }
                } catch (InterruptedException e) {
                    LOGGER.error("生产异常", e);
                }
            }
            
        }
    }
    
    private static class Consumer implements Runnable {
        
        private final DelayQueue<DelayedElement> delayQueue;
        
        public Consumer(DelayQueue<DelayedElement> delayQueue) {
            this.delayQueue = delayQueue;
        }
        
        @Override
        public void run() {
            int i = 0;
            while (true) {
                try {
                    DelayedElement element = delayQueue.take();
                    LOGGER.warn("我消费了一个消息：{},过期时间为:{}", element.getData(),element.getExpireTime());
                    Thread.sleep(1000);
                    if (++i > 20){
                        LOGGER.warn("消费线程退出");
                        break;
                    }
                } catch (InterruptedException e) {
                    LOGGER.error("消费异常", e);
                }
            }
        }
    }
    
    private static class Monitor implements Runnable {
        
        private final DelayQueue<DelayedElement> delayQueue;
        
        public Monitor(DelayQueue<DelayedElement> delayQueue) {
            this.delayQueue = delayQueue;
        }
        
        @Override
        public void run() {
            while (true) {
                LOGGER.error("size={}", delayQueue.size());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    LOGGER.error("获取总数异常", e);
                }
            }
        }
    }
    
    static class DelayedElement implements Delayed {
        
        private Long expireTime;
        
        private String data;
        
        public DelayedElement(Long expireTime, String data) {
            this.expireTime = expireTime;
            this.data = data;
        }
        
        @Override
        public long getDelay(@NotNull TimeUnit unit) {
            return unit.convert(this.expireTime - System.currentTimeMillis(),TimeUnit.MILLISECONDS);
        }
        
        @Override
        public int compareTo(@NotNull Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }
        
        public Long getExpireTime() {
            return expireTime;
        }
        
        public String getData() {
            return data;
        }
    }
}
