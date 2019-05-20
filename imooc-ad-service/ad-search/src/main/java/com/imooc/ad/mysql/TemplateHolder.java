package com.imooc.ad.mysql;

import com.alibaba.fastjson.JSON;
import com.imooc.ad.mysql.constant.OpType;
import com.imooc.ad.mysql.dto.ParseTemplate;
import com.imooc.ad.mysql.dto.TableTemplate;
import com.imooc.ad.mysql.dto.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/5/20 23:00
 */
@Slf4j
@Component
public class TemplateHolder {

    private ParseTemplate template;

    private final JdbcTemplate jdbcTemplate;

    private String SQL_SCHEMA = "select info.TABLE_SCHEMA, info.TABLE_NAME, info.COLUMN_NAME, info.ORDINAL_POSITION " +
            "from information_schema.`COLUMNS` info " +
            "where info.TABLE_SCHEMA = ? and info.TABLE_NAME = ?";

    @Autowired
    public TemplateHolder(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    private void init() {
        loadJson("template.json");
    }

    public TableTemplate getTable(String tableName) {
        return template.getTableTemplateMap().get(tableName);
    }

    private void loadJson(String path) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(path);
        try {
            Template template = JSON.parseObject(inputStream, Charset.defaultCharset(), Template.class);
            this.template = ParseTemplate.parse(template);
            loadMeta();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException("fail to parse json file");
        }
    }

    private void loadMeta() {
        template.getTableTemplateMap().forEach((tableName, tableTemplate) -> {
            List<String> insertFields = tableTemplate.getOpTypeFieldMap().get(OpType.ADD);
            List<String> updateFields = tableTemplate.getOpTypeFieldMap().get(OpType.UPDATE);
            List<String> deleteFields = tableTemplate.getOpTypeFieldMap().get(OpType.DELETE);

            jdbcTemplate.query(SQL_SCHEMA, new Object[]{template.getDatabase(), tableName}, (rs, i) -> {
                int pos = rs.getInt("ORDINAL_POSITION");
                String columnName = rs.getString("COLUMN_NAME");
                if (null != updateFields && updateFields.contains(columnName)
                        || null != deleteFields && deleteFields.contains(columnName)
                        || null != insertFields && insertFields.contains(columnName)) {
                    tableTemplate.getPosMap().put(pos - 1, columnName);
                }
                return null;
            });
        });

    }
}
