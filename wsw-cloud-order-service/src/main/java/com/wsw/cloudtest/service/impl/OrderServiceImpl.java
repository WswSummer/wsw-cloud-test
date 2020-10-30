package com.wsw.cloudtest.service.impl;

import com.wsw.cloudtest.dao.OrderMapper;
import com.wsw.cloudtest.domain.Order;
import com.wsw.cloudtest.service.AccountService;
import com.wsw.cloudtest.service.OrderService;
import com.wsw.cloudtest.service.StorageService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author WangSongWen
 * @Date: Created in 16:35 2020/10/27
 * @Description:
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private AccountService accountService;
    @Autowired
    private StorageService storageService;

    @Override
    public void create(Order order) {
        // 向RabbitMQ中发消息
        sendMessage(order);

        // 1. 创建订单
        orderMapper.create(order);
        // 2. 减库存
        storageService.decrease(order.getProductId(), order.getCount());
        // 3. 扣减账户
        accountService.decrease(order.getUserId(), order.getMoney());
        // 4. 修改订单状态
        orderMapper.update(order.getUserId(), 0);
    }

    public void sendMessage(Order order){
        Map<String, Object> messageMap = new HashMap<>();
        messageMap.put("messageId", UUID.randomUUID());
        messageMap.put("orderMessage", order);
        rabbitTemplate.convertAndSend("fanoutExchange", null, messageMap);
    }
}
