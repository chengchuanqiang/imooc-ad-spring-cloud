package com.imooc.ad.mysql.dto;

import com.github.shyiko.mysql.binlog.event.EventType;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/5/20 23:22
 */
@Data
public class BinlogRowData {

    private TableTemplate table;
    private EventType eventType;
    private List<Map<String, String>> before;
    private List<Map<String, String>> after;

}
