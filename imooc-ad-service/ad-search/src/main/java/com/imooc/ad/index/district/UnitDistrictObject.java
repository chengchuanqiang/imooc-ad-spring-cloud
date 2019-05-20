package com.imooc.ad.index.district;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 地域
 * @Author: ChengChuanQiang
 * @Date: 2019/5/12 8:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitDistrictObject {

    private Long unitId;
    private String province;
    private String city;

    // <String, Set<Long>>
    // province-city
}
