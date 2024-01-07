package com.gzzdsg.happylife.service.impl;

import com.gzzdsg.happylife.domain.po.Food;
import com.gzzdsg.happylife.mapper.FoodMapper;
import com.gzzdsg.happylife.service.FoodService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 食物相关逻辑
 *
 * @author: damei
 */
@Service
public class FoodServiceImpl implements FoodService {

    @Resource
    private FoodMapper foodMapper;

    @Override
    public List<Food> findAllFood(String openId) {
        // 获取用户自己录入的食物
        List<Food> openIdFoods = foodMapper.getAllFoodsByOpenId(openId);
        if (!CollectionUtils.isEmpty(openIdFoods)) {
            return openIdFoods;
        }
        // 全量获取
        openIdFoods = foodMapper.getAllFoods();
        return openIdFoods;
    }
}
