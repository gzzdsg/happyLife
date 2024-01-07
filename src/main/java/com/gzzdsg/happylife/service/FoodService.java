package com.gzzdsg.happylife.service;

import com.gzzdsg.happylife.domain.po.Food;

import java.util.List;

/**
 * 食物相关逻辑
 *
 * @author: damei
 */
public interface FoodService {

    /**
     * 根据openid获取食物的随机池
     *
     * @param openId 用户唯一标识
     * @return 食物随机池
     */
    List<Food> findAllFood(String openId);

}
