package com.wsw.cloudtest.service.impl;

import com.wsw.cloudtest.dao.StorageMapper;
import com.wsw.cloudtest.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author WangSongWen
 * @Date: Created in 17:27 2020/10/27
 * @Description:
 */
@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    private StorageMapper storageMapper;

    @Override
    public void decrease(Long productId, Integer count) {
        storageMapper.decrease(productId, count);
    }
}
