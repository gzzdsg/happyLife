package com.gzzdsg.happylife.service;

import com.gzzdsg.happylife.domain.po.Food;

import java.util.List;

/**
 * 食物相关逻辑
 *
 * @author: damei
 */
public interface FoodService {

    List<Food> findAllFood(String openId);

}
