package com.imooc.ad.search.vo.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 设备信息
 * @Author: ChengChuanQiang
 * @Date: 2019/5/22 23:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device {

    /**
     * 设备id
     */
    private String deviceCode;

    /**
     * mac地址
     */
    private String mac;
    /**
     * IP地址
     */
    private String ip;
    /**
     * 机型编码
     */
    private String model;
    /**
     * 分辨率尺寸
     */
    private String displaySize;
    /**
     * 屏幕尺寸
     */
    private String screenSize;
    /**
     * 设备序列号
     */
    private String serialName;
}
