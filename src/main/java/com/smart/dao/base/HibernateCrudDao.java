package com.smart.dao.base;

import com.smart.commons.Global;
import com.sun.xml.internal.rngom.parse.host.Base;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author walker
 * @version 1.0
 * @className CrudDao
 * @date 2018/1/19 14:19
 * @description:
 */
@Repository
public abstract class HibernateCrudDao<T> implements BaseDao<T> {
    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> clazz;

    /**
     * 获取hibernate的原生session以使用其原生API
     *
     * @return
     */
    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public T get(Serializable primaryKey) {
        return this.hibernateTemplate.get(this.clazz, primaryKey);
    }

    @Override
    public List<T> getAll(T t) {
        return this.hibernateTemplate.loadAll(this.clazz);
    }

    @Override
    public List<T> findList(T t) {
        return this.hibernateTemplate.findByExample(t);
    }

    @Override
    public List<?> find(String hpl, Object... values) {
        return this.hibernateTemplate.find(hpl, values);
    }

    @Override
    public void insert(T o) {
        this.hibernateTemplate.saveOrUpdate(o);
    }

    @Override
    public void batchInsert(List<T> insertList) {
        this.hibernateTemplate.execute(new HibernateCallback<T>() {
            @Override
            public T doInHibernate(Session session) throws HibernateException {
                for (int i = 0; i < insertList.size(); i++) {
                    session.save(insertList.get(i));
                    if (i % Integer.valueOf(Global.getConfig("BATCH_MAX_ROW")) == 0) {
                        session.flush();
                        session.clear();
                    }
                }
                session.flush();
                session.clear();
                return null;
            }
        });
    }

    @Override
    public void update(T o) {
        this.hibernateTemplate.saveOrUpdate(o);
    }

    @Override
    public void batchUpdate(List<T> updateList) {
        this.hibernateTemplate.execute(new HibernateCallback<T>() {
            @Override
            public T doInHibernate(Session session) throws HibernateException {
                for (int i = 0; i < updateList.size(); i++) {
                    session.update(updateList.get(i));
                    if (i % Integer.valueOf(Global.getConfig("batch_max_row")) == 0) {
                        session.flush();
                        session.clear();
                    }
                }
                session.flush();
                session.clear();
                return null;
            }
        });
    }

    @Override
    public void delete(T o) {
        this.hibernateTemplate.delete(o);
    }

    @Override
    public void delete(Serializable primaryKey) {
        this.hibernateTemplate.delete(get(primaryKey));
    }

    //获取泛型模板的类型信息
    public HibernateCrudDao() {
        //处理T的原始类型
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获取<T,U...>的数据类型
        Type[] types = type.getActualTypeArguments();
        if (types.length != 1) {
            throw new IllegalArgumentException("参数错误");
        }
        //设置类型信息
        this.clazz = (Class<T>) types[0];

    }
}
