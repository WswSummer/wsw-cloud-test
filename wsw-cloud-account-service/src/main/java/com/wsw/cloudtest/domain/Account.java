package com.wsw.cloudtest.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author WangSongWen
 * @Date: Created in 17:45 2020/10/27
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Long id;
    private Long userId;
    private Integer total;
    private Integer used;
    private Integer residue;
}
