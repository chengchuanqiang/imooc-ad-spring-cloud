package com.imooc.ad.service;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.DeleteRowsEventData;
import com.github.shyiko.mysql.binlog.event.EventData;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;
import com.github.shyiko.mysql.binlog.event.WriteRowsEventData;

import java.io.IOException;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/5/20 22:20
 */
public class BinlogServiceTest {

    /**
     * insert into ad_unit_keyword(unit_id, keyword) values (1,"标志");
     * update ad_unit_keyword set keyword = "标志2" where unit_id = 1;
     * delete from ad_unit_keyword where unit_id = 1;
     * <p>
     * <p>
     * Write-----------
     * WriteRowsEventData{tableId=124, includedColumns={0, 1, 2}, rows=[[2, 1, 标志]]}
     * <p>
     * Update-----------
     * UpdateRowsEventData{tableId=124, includedColumnsBeforeUpdate={0, 1, 2}, includedColumns={0, 1, 2}, rows=[{before=[2, 1, 标志], after=[2, 1, 标志2]}]}
     * <p>
     * Delete-----------
     * DeleteRowsEventData{tableId=124, includedColumns={0, 1, 2}, rows=[[2, 1, 标志2]]}
     * <p>
     * <p>
     * select info.TABLE_SCHEMA, info.TABLE_NAME, info.COLUMN_NAME, info.ORDINAL_POSITION
     * from information_schema.`COLUMNS` info
     * where info.TABLE_SCHEMA = "imooc_ad_data" and info.TABLE_NAME = "ad_unit_keyword";
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BinaryLogClient client = new BinaryLogClient("127.0.0.1", 3306, "root", "123456");

        client.registerEventListener(event -> {
            EventData data = event.getData();
            if (data instanceof UpdateRowsEventData) {
                System.out.println("Update-----------");
                System.out.println(data.toString());
            } else if (data instanceof WriteRowsEventData) {
                System.out.println("Write-----------");
                System.out.println(data.toString());
            } else if (data instanceof DeleteRowsEventData) {
                System.out.println("Delete-----------");
                System.out.println(data.toString());
            }
        });

        client.connect();
    }
//
//    insert into ad_plan(user_id, plan_name, plan_status, start_date, end_date, create_time, update_time)
//    VALUES (1, "plan", 1, "2019-01-01 00:00:00", "2019-01-01 00:00:00", "2019-01-01 00:00:00", "2019-01-01 00:00:00");

//    Write-----------
//    WriteRowsEventData{tableId=120, includedColumns={0, 1, 2, 3, 4, 5, 6, 7}, rows=[
//    [3, 1, plan, 1, Tue Jan 01 08:00:00 CST 2019, Tue Jan 01 08:00:00 CST 2019, Tue Jan 01 08:00:00 CST 2019, Tue Jan 01 08:00:00 CST 2019]
//]}

}
