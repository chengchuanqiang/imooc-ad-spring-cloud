package com.imooc.ad.utils;

import java.util.Map;
import java.util.function.Supplier;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/5/9 22:24
 */
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
}
