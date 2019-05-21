package com.imooc.ad.index;

import lombok.Getter;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/5/21 23:55
 */
@Getter
public enum DataLevel {
    /**
     * 层级
     */
    LEVEL2("2", "level 2"),
    LEVEL3("3", "level 3"),
    LEVEL4("4", "level 4");
    private String level;
    private String desc;

    DataLevel(String level, String desc) {
        this.level = level;
        this.desc = desc;
    }
}
