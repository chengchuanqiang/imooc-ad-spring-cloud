package com.imooc.ad.service;

import com.imooc.ad.exception.AdException;
import com.imooc.ad.vo.*;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/5/4 13:48
 */
public interface IAdUnitService {

    /**
     * 推广单元创建
     * @param request 请求
     * @return 推广单元
     * @throws AdException 异常
     */
    AdUnitResponse createUnit(AdUnitRequest request) throws AdException;

    AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request) throws AdException;

    AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException;

    AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request) throws AdException;

    CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request) throws AdException;

}
