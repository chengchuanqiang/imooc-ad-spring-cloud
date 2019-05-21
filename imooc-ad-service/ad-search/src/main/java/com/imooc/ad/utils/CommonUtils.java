package com.imooc.ad.utils;

import javafx.scene.input.DataFormat;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/5/9 22:24
 */
@Slf4j
public class CommonUtils {

    public static <K, V> V getOrCreate(K key, Map<K, V> map, Supplier<V> factory) {
        return map.computeIfAbsent(key, k -> factory.get());
    }

    public static String stringConcat(String... args) {
        StringBuilder result = new StringBuilder();
        for (String arg : args) {
            result.append(arg);
            result.append("-");
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    /**
     * Tue Jan 01 08:00:00 CST 2019 转换为 date
     *
     * @param dateString 字符串
     * @return date
     */
    public static Date parseStringDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
            return DateUtils.addHours(dateFormat.parse(dateString), -8);
        } catch (ParseException e) {
            log.error("parseStringDate error {}", dateString);
            return null;
        }
    }


}
