package com.imooc.ad.dump.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/5/12 9:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdUnitKeywordTable {

    private Long unitId;
    private String keyword;
}
