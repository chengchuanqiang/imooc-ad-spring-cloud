package com.imooc.ad.constant;

import lombok.Getter;

/**
 * @Description: 创意类型
 * @Author: ChengChuanQiang
 * @Date: 2019/5/4 9:51
 */
@Getter
public enum CreativeType {

    IMAGE(1, "图片"),
    VIDEO(2, "视频"),
    TEXT(3, "文本");

    private int type;
    private String desc;

    CreativeType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
