package com.imooc.ad.search.vo.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/5/22 23:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdSlot {

    /**
     * 广告为编码
     */
    private String adSlotCode;

    /**
     * 流量类型
     */
    private Integer positionType;

    /**
     * 宽和高
     */
    private Integer width;
    private Integer height;

    /**
     * 广告物料类型：图片、视频
     */
    private List<Integer> type;

    /**
     * 最低出价
     */
    private Integer minCpm;
}
