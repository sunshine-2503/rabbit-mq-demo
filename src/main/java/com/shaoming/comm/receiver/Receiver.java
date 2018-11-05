package com.shaoming.comm.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ShaoMing
 * @version 1.0.0
 * @date 2018/11/5 9:12
 */
@Slf4j
@Component
public class Receiver {

    /**
     * 监听消息队列：hello.queue1
     * @param msg 消息内容
     * @return
     */
    @RabbitListener(queues = "hello.queue1")
    public void processMessage1(String msg) {
        System.out.println(Thread.currentThread().getName() + " 接收到来自hello.queue1队列的消息：" + msg);
        // TODO 业务处理

        /**
         * 问题：如果业务中发生异常，会导致消息消费失败，若配置消费失败继续将消息重新放置队列中可能会出现重复不断的在消费同一条消息的情况（死循环）
         * 问题示例如下：
         */
        int n = 2/0; // 经测试 报错信息会打印在控制台
        log.info("n = " + n);
        /**
         * 问题解决方案：死信队列。
         *      当业务处理发生异常：重复几次后，将消息转发到死信队列中，在死信队列中处理（记录异常的消息）
         *      转发到死信队列只需配置即可，无需手动转发（参考：application.yml 以及 RabbitConfig.java 配置）
         */
    }

//    /**
//     * 监听消息队列：hello.queue2
//     * @param msg 消息内容
//     * @return
//     */
//    @RabbitListener(queues = "hello.queue2")
//    public void processMessage2(String msg) {
//        System.out.println(Thread.currentThread().getName() + " 接收到来自hello.queue2队列的消息：" + msg);
//    }

}
