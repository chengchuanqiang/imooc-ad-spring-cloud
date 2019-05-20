package com.imooc.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/5/4 14:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitItResponse {

    private List<Long> ids;
}
