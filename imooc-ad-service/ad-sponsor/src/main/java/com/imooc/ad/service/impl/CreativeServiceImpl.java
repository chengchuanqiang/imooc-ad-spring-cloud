package com.imooc.ad.service.impl;

import com.imooc.ad.dao.CreativeRepository;
import com.imooc.ad.dao.unit_condition.CreativeUnitRepository;
import com.imooc.ad.entity.Creative;
import com.imooc.ad.exception.AdException;
import com.imooc.ad.service.ICreativeService;
import com.imooc.ad.vo.CreativeRequest;
import com.imooc.ad.vo.CreativeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/5/4 14:23
 */
@Service
public class CreativeServiceImpl implements ICreativeService {

    private final CreativeRepository creativeRepository;

    @Autowired
    public CreativeServiceImpl(CreativeRepository creativeRepository) {
        this.creativeRepository = creativeRepository;
    }

    @Override
    public CreativeResponse createCreative(CreativeRequest request) throws AdException {
        // #todo 校验完善

        Creative creative = creativeRepository.save(request.convertToEntity());
        return new CreativeResponse(creative.getId(), creative.getName());
    }
}
