package com.wsw.cloudtest.service.impl;

import com.rabbitmq.client.Channel;
import com.wsw.cloudtest.domain.Storage;
import com.wsw.cloudtest.mapper.StorageMapper;
import com.wsw.cloudtest.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Author WangSongWen
 * @Date: Created in 17:27 2020/10/27
 * @Description:
 */
@Slf4j
@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    private StorageMapper storageMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void decrease(Long productId, Integer count) {
        storageMapper.decrease(productId, count);
    }

    @Override
    public List<Storage> selectStorages() {
        return storageMapper.selectStorages();
    }
}
