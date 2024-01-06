package com.gzzdsg.happylife.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机工具类
 *
 * @author: damei
 */
public class RandomUtils {

    /**
     * 指定范围的随机一个整数
     *
     * @param bound 边界
     * @return 随机值
     */
    public static int randomInt(int bound) {
        return ThreadLocalRandom.current().nextInt(bound);
    }

}
