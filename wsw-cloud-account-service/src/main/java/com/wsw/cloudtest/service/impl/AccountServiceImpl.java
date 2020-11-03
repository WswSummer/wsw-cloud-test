package com.wsw.cloudtest.service.impl;

import com.rabbitmq.client.Channel;
import com.wsw.cloudtest.mapper.AccountMapper;
import com.wsw.cloudtest.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

/**
 * @Author WangSongWen
 * @Date: Created in 17:59 2020/10/27
 * @Description:
 */
@Slf4j
@Service
@RabbitListener(queues = "queueAccount")  // 监听的队列名称queueAccount
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    // 从RabbitMQ中接收消息
    @RabbitHandler
    public void messageReceive(Channel channel, Message message){
        log.info("wsw-cloud-account-service接收到了消息: " + Arrays.toString(message.getBody()));
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void decrease(Long userId, BigDecimal money) {
        accountMapper.decrease(userId, money);
    }
}
