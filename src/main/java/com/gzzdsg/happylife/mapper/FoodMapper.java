package com.gzzdsg.happylife.mapper;

import com.gzzdsg.happylife.domain.po.Food;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 食物的数据操作类
 *
 * @author: damei
 */
@Mapper
public interface FoodMapper{

    @Select("select * from food")
    List<Food> getAllFoods();

}
