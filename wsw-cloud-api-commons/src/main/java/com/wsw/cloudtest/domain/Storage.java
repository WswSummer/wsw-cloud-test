package com.wsw.cloudtest.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author WangSongWen
 * @Date: Created in 17:20 2020/10/27
 * @Description: 库存实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Storage {
    private Long id;
    private Long productId;
    private Integer total;
    private Integer used;
    private Integer residue;
}
