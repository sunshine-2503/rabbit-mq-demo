package com.shaoming.comm.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShaoMing
 * @version 1.0.0
 * @date 2018/11/2 18:11
 */
@Configuration
public class RabbitConfig {

    // 死信的交换机名
    public static final String DEAD_LETTER_EXCHANGE = "DEAD_LETTER_EXCHANGE";
    // 死信队列名（替补队列）
    public static final String DEAD_LETTER_QUEUE = "DEAD_LETTER_QUEUE";
    // 死信队列 ROUTING_KEY
    public static final String DEAD_ROUTING_KEY = "DEAD_ROUTING_KEY";

    // 业务队列交换机名
    public static final String TOPIC_EXCHANGE = "TOPIC_EXCHANGE";

    // 声明死信队列（替补作用）
    @Bean
    public Queue deadLetterQueue(){
        return new Queue(DEAD_LETTER_QUEUE);
    }

    // 声明死信队列交互器
    @Bean
    public TopicExchange deadLetterExchange() {
        return new TopicExchange(DEAD_LETTER_EXCHANGE, true, false);
    }

    // 绑定死信队列
    @Bean
    public Binding deadLetterBinding(Queue deadLetterQueue, TopicExchange deadLetterExchange){
        return BindingBuilder.bind(deadLetterQueue).to(deadLetterExchange).with(DEAD_ROUTING_KEY);
    }

    /**
     * 业务消息队列
     */
    // 声明【业务交互器】
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    // 声明【业务队列1】
    @Bean
    public Queue queue1() {
        Map<String,Object> map = new HashMap<>();
        // 设置该Queue的死信的信箱
        map.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        // 设置死信routingKey
        map.put("x-dead-letter-routing-key", DEAD_ROUTING_KEY);
        return new Queue("hello.queue1",true,false,false, map); // true 表示持久化该队列
    }

    // 绑定【业务队列1】
    @Bean
    public Binding binding1(Queue queue1, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue1).to(topicExchange).with("BIND_KEY");
    }

//    @Bean
//    public Queue queue2() {
//        return new Queue("hello.queue2", true);
//    }

//    @Bean
//    public Binding binding2(Queue queue2, TopicExchange topicExchange) {
//        return BindingBuilder.bind(queue2).to(topicExchange).with("key.2");
//    }

}
