package cn.followtry.mq.rabbitmq;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

/**
 * Created by followtry on 2017/1/17.
 */
public class RabbitMQDemo {

    private static final Logger LOGGER= LoggerFactory.getLogger(RabbitMQDemo.class);
    private static String host ="localhost";
    private static String vHost = "jingzz_rabbitmq";
    private static String username ="jingzz";
    private static String pwd ="jingzz";

    public static void main(String[] args) throws InterruptedException {
        //设置rabbitmq服务器连接
        CachingConnectionFactory cf = new CachingConnectionFactory(host);
//        cf.setVirtualHost(vHost);
        cf.setUsername(username);
        cf.setPassword(pwd);

        RabbitAdmin rabbitAdmin = new RabbitAdmin(cf);

        //设置队列，交换器并将其绑定到broker上
        String queueName = "myQueue";
        Queue myQueue = new Queue(queueName);
        rabbitAdmin.declareQueue(myQueue);
        String myExchangeName = "amq.topic";
        TopicExchange exchange = new TopicExchange(myExchangeName);
        rabbitAdmin.declareBinding(BindingBuilder.bind(myQueue).to(exchange).with("foo.*"));

        //设置监听器和容器
        SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer(cf);
        Object listener = new Object() {
            public void handleMessage(String foo) {
                System.out.println("处理信息："+foo);
            }
        };

        MessageListenerAdapter listenerAdapter = new MessageListenerAdapter(listener);

        listenerContainer.setMessageListener(listenerAdapter);
        listenerContainer.setQueueNames(queueName);
        listenerContainer.start();

        RabbitTemplate rabbitTemplate = new RabbitTemplate(cf);
        rabbitTemplate.convertAndSend(myExchangeName,"foo.bar","hello rabbitmq,hello idea,hello world");
        TimeUnit.SECONDS.sleep(1);
        listenerContainer.stop();
    }
}
