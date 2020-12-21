package com.wsw.cloudtest.service;

import com.wsw.cloudtest.domain.Storage;

import java.util.List;

/**
 * @Author WangSongWen
 * @Date: Created in 17:26 2020/10/27
 * @Description:
 */
public interface StorageService {
    void decrease(Long productId, Integer count);

    List<Storage> selectStorages();
}
