package com.imooc.ad.service;

import com.imooc.ad.exception.AdException;
import com.imooc.ad.vo.CreativeRequest;
import com.imooc.ad.vo.CreativeResponse;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/5/4 14:20
 */
public interface ICreativeService {

    /**
     * 创建创意
     * @param request 请求
     * @return 创意
     * @throws AdException 异常
     */
    CreativeResponse createCreative(CreativeRequest request) throws AdException;
}
