package com.imooc.ad.handler;

import com.alibaba.fastjson.JSON;
import com.imooc.ad.dump.table.*;
import com.imooc.ad.index.DataTable;
import com.imooc.ad.index.IndexAware;
import com.imooc.ad.index.adplan.AdPlanIndex;
import com.imooc.ad.index.adplan.AdPlanObject;
import com.imooc.ad.index.adunit.AdUnitIndex;
import com.imooc.ad.index.adunit.AdUnitObject;
import com.imooc.ad.index.creative.CreativeIndex;
import com.imooc.ad.index.creative.CreativeObject;
import com.imooc.ad.index.creativeunit.CreativeUnitIndex;
import com.imooc.ad.index.creativeunit.CreativeUnitObject;
import com.imooc.ad.index.district.UnitDistrictIndex;
import com.imooc.ad.index.interest.UnitItIndex;
import com.imooc.ad.index.keyword.UnitKeywordIndex;
import com.imooc.ad.mysql.constant.OpType;
import com.imooc.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 1、索引之间存在层级的划分，也就是依赖关系的划分
 * 2、加载全量的索引其实是增量索引 “增加” 的一种特殊实现
 * @Author: ChengChuanQiang
 * @Date: 2019/5/12 11:03
 */
@Slf4j
public class AdLevelDataHandler {

    /**
     * 广告计划索引操作
     *
     * @param planTable 广告计划
     * @param type      操作方式
     */
    public static void handleLevel2(AdPlanTable planTable, OpType type) {
        AdPlanObject planObject = new AdPlanObject(planTable.getId(), planTable.getUserId(), planTable.getPlanStatus(), planTable.getStartDate(), planTable.getEndDate());
        handleBinlogEvent(
                DataTable.of(AdPlanIndex.class),
                planObject.getPlanId(),
                planObject,
                type
        );
    }

    /**
     * 创意索引计划
     *
     * @param creativeTable 创意
     * @param type          操作方式
     */
    public static void handleLevel2(AdCreativeTable creativeTable, OpType type) {
        CreativeObject creativeObject = new CreativeObject(
                creativeTable.getAdId(),
                creativeTable.getName(),
                creativeTable.getType(),
                creativeTable.getMaterialType(),
                creativeTable.getHeight(),
                creativeTable.getWidth(),
                creativeTable.getAuditStatus(),
                creativeTable.getAdUrl()
        );
        handleBinlogEvent(
                DataTable.of(CreativeIndex.class),
                creativeObject.getAdId(),
                creativeObject,
                type
        );
    }

    /**
     * 广告单元
     *
     * @param unitTable 广告单元
     * @param type      操作类型
     */
    public static void handleLevel3(AdUnitTable unitTable, OpType type) {
        AdPlanObject adPlanObject = DataTable.of(AdPlanIndex.class).get(unitTable.getPlanId());
        if (null == adPlanObject) {
            log.error("handleLevel3 found AdPlanObject error: {}", unitTable.getPlanId());
            return;
        }

        AdUnitObject adUnitObject = new AdUnitObject(unitTable.getUnitId(), unitTable.getUnitStatus(), unitTable.getPositionType(), unitTable.getPlanId(), adPlanObject);
        handleBinlogEvent(
                DataTable.of(AdUnitIndex.class),
                adUnitObject.getUnitId(),
                adUnitObject,
                type);

    }

    /**
     * 创意与广告单元关联关系
     *
     * @param creativeUnitTable 关联关系
     * @param type              类型
     */
    public static void handleLevel3(AdCreativeUnitTable creativeUnitTable, OpType type) {
        if (type == OpType.UPDATE) {
            log.error("CreateUnitIndex not support update");
            return;
        }

        AdUnitObject unitObject = DataTable.of(AdUnitIndex.class).get(creativeUnitTable.getUnitId());
        CreativeObject creativeObject = DataTable.of(CreativeIndex.class).get(creativeUnitTable.getAdId());

        if (null == unitObject || null == creativeObject) {
            log.error("AdCreativeUnitTable index error: {}", JSON.toJSONString(creativeUnitTable));
            return;
        }

        CreativeUnitObject creativeUnitObject = new CreativeUnitObject(creativeUnitTable.getAdId(), creativeUnitTable.getUnitId());

        handleBinlogEvent(
                DataTable.of(CreativeUnitIndex.class),
                CommonUtils.stringConcat(creativeUnitObject.getAdId().toString(), creativeUnitObject.getUnitId().toString()),
                creativeUnitObject,
                type
        );
    }

    /**
     * 广告单元区域筛选
     *
     * @param unitDistrictTable 区域
     * @param type              操作类型
     */
    public static void handleLevel4(AdUnitDistrictTable unitDistrictTable, OpType type) {

        if (type == OpType.UPDATE) {
            log.error("district index can not support update");
            return;
        }

        AdUnitObject unitObject = DataTable.of(AdUnitIndex.class).get(unitDistrictTable.getUnitId());
        if (null == unitObject) {
            log.error("AdUnitDistrictTable index error: {}", unitDistrictTable.getUnitId());
            return;
        }

        String key = CommonUtils.stringConcat(unitDistrictTable.getProvince(), unitDistrictTable.getProvince());
        Set<Long> value = new HashSet<>(Collections.singleton(unitDistrictTable.getUnitId()));
        handleBinlogEvent(
                DataTable.of(UnitDistrictIndex.class),
                key,
                value,
                type
        );
    }

    /**
     * 广告单元标签筛选
     *
     * @param unitItTable 标签
     * @param type        操作类型
     */
    public static void handleLevel4(AdUnitItTable unitItTable, OpType type) {

        if (type == OpType.UPDATE) {
            log.error("it index can not support update");
            return;
        }

        AdUnitObject unitObject = DataTable.of(AdUnitIndex.class).get(unitItTable.getUnitId());
        if (unitObject == null) {
            log.error("AdUnitItTable index error: {}", unitItTable.getUnitId());
            return;
        }

        Set<Long> value = new HashSet<>(Collections.singleton(unitItTable.getUnitId()));
        handleBinlogEvent(
                DataTable.of(UnitItIndex.class),
                unitItTable.getItTag(),
                value,
                type
        );
    }

    /**
     * 广告单元关键词筛选
     *
     * @param keywordTable 关键词
     * @param type         操作类型
     */
    public static void handleLevel4(AdUnitKeywordTable keywordTable, OpType type) {

        if (type == OpType.UPDATE) {
            log.error("keyword index can not support update");
            return;
        }

        AdUnitObject unitObject = DataTable.of(AdUnitIndex.class).get(keywordTable.getUnitId());
        if (unitObject == null) {
            log.error("AdUnitKeywordTable index error: {}", keywordTable.getUnitId());
            return;
        }

        Set<Long> value = new HashSet<>(Collections.singleton(keywordTable.getUnitId()));
        handleBinlogEvent(
                DataTable.of(UnitKeywordIndex.class),
                keywordTable.getKeyword(),
                value,
                type
        );
    }

    private static <K, V> void handleBinlogEvent(IndexAware<K, V> index, K key, V value, OpType type) {
        switch (type) {
            case ADD:
                index.add(key, value);
                break;
            case UPDATE:
                index.update(key, value);
            case DELETE:
                index.delete(key, value);
                break;
            case OTHER:
                break;
            default:
                break;
        }
    }
}
