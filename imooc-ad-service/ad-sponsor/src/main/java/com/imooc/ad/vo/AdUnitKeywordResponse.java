package com.imooc.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/5/4 14:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitKeywordResponse {

    private List<Long> ids;
}
