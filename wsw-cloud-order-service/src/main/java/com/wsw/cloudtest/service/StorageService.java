package com.wsw.cloudtest.service;

import com.wsw.cloudtest.domain.CommonResult;
import com.wsw.cloudtest.domain.Storage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author WangSongWen
 * @Date: Created in 16:45 2020/10/27
 * @Description: 库存服务接口
 */
@FeignClient(value = "wsw-cloud-storage-service")
public interface StorageService {
    /**
     * feign服务调用 减库存接口
     * @param productId
     * @param count
     * @return
     */
    @PostMapping("/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);

    @GetMapping("/storage/selectAll")
    List<Storage> selectStorages();
}
