package com.wsw.cloudtest.service.impl;

import com.wsw.cloudtest.mapper.AccountMapper;
import com.wsw.cloudtest.service.AccountService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @Author WangSongWen
 * @Date: Created in 17:59 2020/10/27
 * @Description:
 */
@Service
@RabbitListener(queues = "queueAccount")  // 监听的队列名称queueAccount
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    // 从RabbitMQ中接收消息
    @RabbitHandler
    public void messageReceive(Map<String, Object> message){
        System.out.println("wsw-cloud-account-service接收到了消息: " + message.toString());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void decrease(Long userId, BigDecimal money) {
        accountMapper.decrease(userId, money);
    }
}
