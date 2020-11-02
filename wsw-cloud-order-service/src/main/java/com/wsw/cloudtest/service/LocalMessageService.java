package com.wsw.cloudtest.service;

import com.wsw.cloudtest.domain.LocalMessage;

import java.util.List;

/**
 * @Author WangSongWen
 * @Date: Created in 15:16 2020/11/2
 * @Description:
 */
public interface LocalMessageService {
    void insertLocalMessage(LocalMessage localMessage);

    void updateLocalMessage(Long localMessageId, Integer status);

    List<LocalMessage> selectFailMessage(Integer status);
}
