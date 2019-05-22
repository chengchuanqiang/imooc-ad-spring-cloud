package com.imooc.ad.search.vo.feature;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/5/22 23:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeywordFeature {
    private List<String> keywords;
}
