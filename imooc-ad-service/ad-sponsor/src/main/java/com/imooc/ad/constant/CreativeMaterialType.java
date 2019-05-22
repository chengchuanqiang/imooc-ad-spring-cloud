package com.imooc.ad.constant;

import lombok.Getter;

/**
 * @Description: 物料类型
 * @Author: ChengChuanQiang
 * @Date: 2019/5/4 9:54
 */
@Getter
public enum CreativeMaterialType {

    /**
     * 物料类型
     */
    JPG(1, "jpg"),
    BMP(2, "bmp"),

    MP4(3, "map4"),
    AVI(4, "avi"),

    TXT(5, "txt");

    private int type;
    private String desc;

    CreativeMaterialType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

}
