package com.smart.dao.base;

import java.io.Serializable;
import java.util.List;

/**
 * @author walker
 * @version 1.0
 * @className CrudDao
 * @date 2018/1/19 14:04
 * @description： 持久层CRUD操作基类
 */
public interface BaseDao<T> extends Serializable {

    /**
     * 根据主键查询
     *
     * @param primaryKey 主键
     * @return
     */
    T get(Serializable primaryKey);

    /**
     * 获取所有对象
     *
     * @param t
     * @return
     */
    List<T> getAll(T t);

    /**
     * 根据条件进行查询
     *
     * @param t
     * @return
     */
    T get(T t);

    /**
     * 查询全部
     *
     * @param t 查询条件
     * @return
     */
    List<T> findAllList(T t);

    /**
     * 按条件查询List
     *
     * @param t
     * @return
     */
    List<T> findList(T t);

    /**
     * 通过hql语句查询
     *
     * @param hpl
     * @return
     */
    List<?> find(String hpl, Object... values);

    /**
     * 新增
     *
     * @param t
     */
    void insert(T t);

    /**
     * 批量新增
     *
     * @param insertList
     */
    void batchInsert(List<T> insertList);

    /**
     * 更新
     *
     * @param t
     */
    void update(T t);

    /**
     * 批量更新
     *
     * @param updateList
     */
    void batchUpdate(List<T> updateList);

    /**
     * 删除
     *
     * @param t
     */
    void delete(T t);

    /**
     * 根据主键直接删除
     *
     * @param primaryKey 主键
     */
    void delete(Serializable primaryKey);

    /**
     * 批量删除
     *
     * @param deleteList
     */
    void batchDelete(List<T> deleteList);

}
