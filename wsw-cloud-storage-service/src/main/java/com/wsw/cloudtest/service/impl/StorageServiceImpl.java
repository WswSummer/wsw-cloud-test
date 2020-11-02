package com.wsw.cloudtest.service.impl;

import com.wsw.cloudtest.mapper.StorageMapper;
import com.wsw.cloudtest.service.StorageService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author WangSongWen
 * @Date: Created in 17:27 2020/10/27
 * @Description:
 */
@Service
@RabbitListener(queues = "queueStorage")  // 监听的队列名称queueStorage
public class StorageServiceImpl implements StorageService {
    @Autowired
    private StorageMapper storageMapper;

    // 从RabbitMQ中接收消息
    @RabbitHandler
    public void messageReceive(Map<String, Object> message){
        System.out.println("wsw-cloud-storage-service接收到了消息: " + message.toString());
    }

    @Override
    public void decrease(Long productId, Integer count) {
        storageMapper.decrease(productId, count);
    }
}
