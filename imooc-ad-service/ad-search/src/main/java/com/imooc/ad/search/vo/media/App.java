package com.imooc.ad.search.vo.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 应用信息
 * @Author: ChengChuanQiang
 * @Date: 2019/5/22 23:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class App {

    /**
     * 应用编码
     */
    private String appCode;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用包名
     */
    private String packageName;

    /**
     * activity 名称
     */
    private String activityName;
}
