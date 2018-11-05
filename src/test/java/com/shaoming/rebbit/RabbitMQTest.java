package com.shaoming.rebbit;

import com.shaoming.comm.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ShaoMing
 * @version 1.0.0
 * @date 2018/11/5 9:32
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMQTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendTest() {
        String content = "哈哈！";
        log.info("============开始发送消息===========");
        log.info("消息内容：" + content);
        rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE, "BIND_KEY", content);
        log.info("============结束发送消息===========");
    }

}
