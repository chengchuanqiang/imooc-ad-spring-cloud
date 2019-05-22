package com.imooc.ad.index;

import lombok.Getter;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/5/23 0:24
 */
@Getter
public enum  CommonStatus {

    /**
     * 状态
     */
    VALID(1, "有效状态"),
    INVALID(0, "无效状态");

    private Integer status;
    private String desc;

    CommonStatus(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
