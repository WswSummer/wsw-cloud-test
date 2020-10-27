package com.wsw.cloudtest.service.impl;

import com.wsw.cloudtest.dao.AccountMapper;
import com.wsw.cloudtest.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @Author WangSongWen
 * @Date: Created in 17:59 2020/10/27
 * @Description:
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        accountMapper.decrease(userId, money);
    }
}
