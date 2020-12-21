package com.wsw.cloudtest.controller;

import com.wsw.cloudtest.domain.CommonResult;
import com.wsw.cloudtest.domain.Storage;
import com.wsw.cloudtest.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author WangSongWen
 * @Date: Created in 17:39 2020/10/27
 * @Description:
 */
@RestController
public class StorageController {
    @Autowired
    private StorageService storageService;

    @PostMapping("/storage/decrease")
    public CommonResult decrease(Long productId, Integer count){
        storageService.decrease(productId, count);
        return new CommonResult(200, "删减库存成功");
    }

    @GetMapping("/storage/selectAll")
    public List<Storage> selectStorages(){
        return storageService.selectStorages();
    }
}
