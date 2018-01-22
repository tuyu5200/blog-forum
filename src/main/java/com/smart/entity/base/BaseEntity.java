package com.smart.entity.base;

import java.io.Serializable;

/**
 * @author walker
 * @version 1.0
 * @className BaseEntity
 * @date 2018/1/19 13:58
 * @description：
 */
public class BaseEntity<T> implements Serializable {
    //数据库做逻辑删除时启用
    /**
     * 数据未删除
     */
    public static final String DEL_FLAG_NORMAL = "0";
    /**
     * 已删除删除
     */
    public static final String DEL_FLAG_DELETE = "1";


}
