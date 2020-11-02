package com.wsw.cloudtest.rabbitmq;

import com.wsw.cloudtest.domain.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author WangSongWen
 * @Date: Created in 10:01 2020/11/2
 * @Description:
 */
@Service
public class RabbitService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(Order order) throws Exception{
        Map<String, Object> messageMap = new HashMap<>();
        messageMap.put("messageId", UUID.randomUUID());
        messageMap.put("orderMessage", order);
        rabbitTemplate.convertAndSend("fanoutExchange", null, messageMap);
    }
}
