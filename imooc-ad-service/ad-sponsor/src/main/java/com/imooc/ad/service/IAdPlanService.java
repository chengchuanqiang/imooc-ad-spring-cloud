package com.imooc.ad.service;

import com.imooc.ad.entity.AdPlan;
import com.imooc.ad.exception.AdException;
import com.imooc.ad.vo.AdPlanGetRequest;
import com.imooc.ad.vo.AdPlanRequest;
import com.imooc.ad.vo.AdPlanResponse;

import java.util.List;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/5/4 10:30
 */
public interface IAdPlanService {

    /**
     * 创建推广计划
     *
     * @param request 请求
     * @return 推广计划
     * @throws AdException 异常
     */
    AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException;

    /**
     * 获取推广计划
     *
     * @param request 请求
     * @return 推广计划
     * @throws AdException 异常
     */
    List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException;


    /**
     * 修改推广计划
     *
     * @param request 请求
     * @return 推广计划
     * @throws AdException 异常
     */
    AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException;

    /**
     * 删除推广计划
     *
     * @param request 请求
     * @throws AdException 异常
     */
    void deleteAdPlan(AdPlanRequest request) throws AdException;
}
