//package com.wsw.cloudtest.service.impl;
//
//import cn.hutool.core.collection.CollectionUtil;
//import com.alibaba.fastjson.JSONObject;
//import com.wsw.cloudtest.domain.Order;
//import com.wsw.cloudtest.domain.Storage;
//import com.wsw.cloudtest.message.RabbitService;
//import com.wsw.cloudtest.service.MiusaService;
//import com.wsw.cloudtest.service.StorageService;
//import io.lettuce.core.RedisClient;
//import org.redisson.api.RLock;
//import org.redisson.api.RedissonClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.List;
//
///**
// * @Author WangSongWen
// * @Date: Created in 9:19 2020/12/21
// * @Description:
// */
//@Service
//public class MiusaServiceImpl implements MiusaService {
//    @Autowired
//    private StorageService storageService;
//    @Autowired
//    private RedisClient redisClient;
//    @Autowired
//    private RedissonClient redissonClient;
//    @Autowired
//    private RabbitService rabbitService;
//
//    /**
//     * @description: 活动开始
//     * @author: wangsongwen
//     * @date: 2020/12/21 10:05
//     **/
//    public String startMiusa(String goodsId, String username){
//        String key = username + ":" + goodsId;
//        String secId = goodsId;
//
//        Long value = (Long) redisClient.get(key);
//        if (value != null) {
//            return "exist";
//        }
//
//        Storage sec = storageService.selectStorageById(secId);
//        boolean flag = queryStartTime(sec);
//        if (!flag) {
//            return "notTime";
//        }
//
//        // 分布式锁
//        RLock rLock = redissonClient.getLock("miusa");
//        rLock.lock();
//        if (sec.getTotal() > 0){
//            // 减库存
//            storageService.decrease(sec.getProductId(), sec.getCount());
//            redisClient.set(key, System.currentTimeMillis(), 60*30);
//
//            Order newOrder = new Order();
//            newOrder.setCreatetime(new Date());
//            newOrder.setGoodsid(Integer.parseInt(goodsid));
//            newOrder.setStatus("未付款");
//            newOrder.setUsername(username);
//
//            String json = JSONObject.toJSONString(newOrder);
//
//            rabbitService.sendMessage(json); // 异步下单
//            rLock.unlock(); // 解锁
//
//            return "success";
//        }else {
//            rLock.unlock();
//            return "failed";
//        }
//    }
//
//    /**
//     * @description: 初始化,将mysql中的商品信息缓存到redis中
//     * @author: wangsongwen
//     * @date: 2020/12/21 9:56
//     **/
//    public List<Storage> selectStorages(){
//        List<Storage> storages = (List<Storage>)redisClient.get("secgoods");
//        if (CollectionUtil.isEmpty(storages)){
//            storages = storageService.selectStorages();
//            redisClient.set("secgoods", storages);
//        }
//    }
//
//    /**
//     * @description: 秒杀活动是否开始
//     * @author: wangsongwen
//     * @date: 2020/12/21 10:00
//     **/
//    public boolean queryStartTime(Storage storage){
//        Date date = new Date();
//        Date startTime = storage.getStartTime();
//        return startTime.getTime() <= date.getTime();
//    }
//
//
//}
