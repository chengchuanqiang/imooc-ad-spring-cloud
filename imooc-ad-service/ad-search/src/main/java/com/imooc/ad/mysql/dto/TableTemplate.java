package com.imooc.ad.mysql.dto;

import com.imooc.ad.mysql.constant.OpType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/5/20 22:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableTemplate {

    private String tableName;
    private String level;

    private Map<OpType, List<String>> opTypeFieldMap = new HashMap<>();
    /**
     * 字段索引 -> 字段名
     */
    private Map<Integer, String> posMap= new HashMap<>();
}
