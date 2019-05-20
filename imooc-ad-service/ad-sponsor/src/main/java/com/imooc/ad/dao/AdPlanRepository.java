package com.imooc.ad.dao;

import com.imooc.ad.entity.AdPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/5/4 9:59
 */
public interface AdPlanRepository extends JpaRepository<AdPlan, Long> {

    /**
     * 根据id和用户id查询
     *
     * @param id     id
     * @param userId 用户id
     * @return 推广计划
     */
    AdPlan findByIdAndUserId(Long id, Long userId);

    /**
     * 根据id集合和用户id查询
     *
     * @param id     id
     * @param userId 用户id
     * @return 推广计划
     */
    List<AdPlan> findAllByIdInAndUserId(List<Long> id, Long userId);

    /**
     * 根据用户id和计划名称查询
     *
     * @param userId   用户id
     * @param planName 计划name
     * @return 推广计划
     */
    AdPlan findByUserIdAndPlanName(Long userId, String planName);

    /**
     * 根据推广计划状态查询
     *
     * @param planStatus 计划状态
     * @return 推广计划
     */
    List<AdPlan> findAllByPlanStatus(Integer planStatus);

}
