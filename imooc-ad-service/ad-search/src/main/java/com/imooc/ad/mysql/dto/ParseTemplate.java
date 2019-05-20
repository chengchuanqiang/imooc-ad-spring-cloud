package com.imooc.ad.mysql.dto;

import com.imooc.ad.mysql.constant.OpType;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/5/20 22:41
 */
@Data
public class ParseTemplate {

    private String database;
    private Map<String, TableTemplate> tableTemplateMap = new HashMap<>();

    public static ParseTemplate parse(Template template) {
        ParseTemplate parseTemplate = new ParseTemplate();
        parseTemplate.setDatabase(template.getDatabase());

        for (JsonTable table : template.getTableList()) {
            String tableName = table.getTableName();
            Integer tableLevel = table.getLevel();

            TableTemplate tableTemplate = new TableTemplate();
            tableTemplate.setTableName(tableName);
            tableTemplate.setLevel(tableLevel.toString());

            parseTemplate.tableTemplateMap.put(tableName, tableTemplate);

            // 遍历操作类型对应的列
            Map<OpType, List<String>> opTypeFieldMap = tableTemplate.getOpTypeFieldMap();

            for (JsonTable.Column column : table.getInsert()) {
                getAndCreateIfNeed(OpType.ADD, opTypeFieldMap, ArrayList::new).add(column.getColumn());
            }

            for (JsonTable.Column column : table.getUpdate()) {
                getAndCreateIfNeed(OpType.UPDATE, opTypeFieldMap, ArrayList::new).add(column.getColumn());
            }

            for (JsonTable.Column column : table.getDelete()) {
                getAndCreateIfNeed(OpType.DELETE, opTypeFieldMap, ArrayList::new).add(column.getColumn());
            }
        }

        return parseTemplate;
    }

    private static <K, V> V getAndCreateIfNeed(K key, Map<K, V> map, Supplier<V> factory) {
        return map.computeIfAbsent(key, k -> factory.get());
    }
}
