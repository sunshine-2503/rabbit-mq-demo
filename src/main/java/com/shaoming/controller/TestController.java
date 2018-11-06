package com.shaoming.controller;

import com.shaoming.comm.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ShaoMing
 * @version 1.0.0
 * @date 2018/11/6 18:37
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 测试方法
     * @param str 消息内容
     */
    @GetMapping("/send")
    public String sendTest(@RequestParam("str") String str) {
//        String content = "哈哈！";
        log.info("============开始发送消息===========");
        log.info("消息内容：" + str);
        rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE, "BIND_KEY", str);
        log.info("============结束发送消息===========");
        return "success";
    }

}
