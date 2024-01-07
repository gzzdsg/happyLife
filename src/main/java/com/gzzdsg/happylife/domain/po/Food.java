package com.gzzdsg.happylife.domain.po;

import lombok.Data;

/**
 * 食物类
 *
 * @author: damei
 */
@Data
public class Food {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 食物名称
     */
    private String name;

    /**
     * 录入人
     */
    private String openId;

    /**
     * 录入坐标纬度
     */
    private String locationX;

    /**
     * 录入坐标经度
     */
    private String locationY;

    /**
     * 位置信息
     */
    private String label;
}
