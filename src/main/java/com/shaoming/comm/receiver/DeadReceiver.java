package com.shaoming.comm.receiver;

import com.shaoming.comm.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 死信队列消息监听
 * @author ShaoMing
 * @version 1.0.0
 * @date 2018/11/5 15:44
 */
@Slf4j
@Component
public class DeadReceiver {

    /**
     * 监听死信队列消息（监听替补队列）
     * @param msg 消息内容
     */
    @RabbitListener(queues = RabbitConfig.DEAD_LETTER_QUEUE)
    public void deadLetterProcess(String msg) {
        // TODO 业务处理
        log.info("死信队列消息：" + msg);
    }

}
