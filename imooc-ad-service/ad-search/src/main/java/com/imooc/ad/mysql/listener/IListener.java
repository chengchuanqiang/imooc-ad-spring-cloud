package com.imooc.ad.mysql.listener;

import com.imooc.ad.mysql.dto.BinlogRowData;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/5/20 23:25
 */
public interface IListener {

    void register();

    void onEvent(BinlogRowData eventData);
}
