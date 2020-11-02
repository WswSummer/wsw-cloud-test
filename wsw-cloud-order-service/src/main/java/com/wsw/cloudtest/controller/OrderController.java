package com.wsw.cloudtest.controller;

import com.wsw.cloudtest.domain.CommonResult;
import com.wsw.cloudtest.domain.Order;
import com.wsw.cloudtest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author WangSongWen
 * @Date: Created in 17:04 2020/10/27
 * @Description:
 */
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/order/create")
    public CommonResult testCreate(Order order) {
        orderService.createOrder(order);
        return new CommonResult(200, "订单创建成功");
    }
}
