package com.imooc.ad.search.vo.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 地理位置信息
 * @Author: ChengChuanQiang
 * @Date: 2019/5/22 23:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Geo {

    /**
     * 经度
     */
    private Float latitude;
    /**
     * 维度
     */
    private Float longitude;

    private String city;
    private String province;
}
