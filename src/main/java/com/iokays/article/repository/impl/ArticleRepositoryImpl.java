package com.iokays.article.repository.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.iokays.article.domain.Article;
import com.iokays.article.repository.plus.ArticleRepositoryPlus;
import com.iokays.column.domain.Column;

public class ArticleRepositoryImpl implements ArticleRepositoryPlus {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Integer deleteById(Serializable id) {
        Query query = entityManager.createQuery("delete from Article t1 where t1.id =:id");
        return query.setParameter("id", id).executeUpdate();
    }

    public Integer update(Serializable id, Map<String, Object> map) throws Exception {

        String jpql = "update Article set";
        Field[] fields = Article.class.getDeclaredFields();

        Map<String, Object> paramMap = new HashMap<String, Object>();

        boolean mark = true;
        for (int i = 0, length = fields.length; i < length; ++i) {
            final String name = fields[i].getName();
            final Class<?> type = fields[i].getType();

            if (type == String.class) {
                if (map.containsKey(name)) {
                    paramMap.put(name, map.get(name).toString());
                    jpql += mark ? " " : ", ";
                    jpql += name + "= :" + name;
                    mark = false;
                }

            } else if (type == Integer.class) {
                if (map.containsKey(name)) {
                    paramMap.put(name, Integer.valueOf(map.get(name).toString()));
                    jpql += mark ? " " : ", ";
                    jpql += name + "= :" + name;
                    mark = false;
                }
            } else if (type == Column.class) {
                if (map.containsKey(name + ".id")) {
                    paramMap.put(name + "Id", map.get(name + ".id").toString());
                    jpql += mark ? " " : ", ";
                    jpql += (name + ".id") + "= :" + (name + "Id");
                    mark = false;
                }
            }
        }

        if (paramMap.size() != map.size()) throw new Exception("param name error");

        jpql += " where id = :id";
        Query query = entityManager.createQuery(jpql);

        Iterator<Entry<String, Object>> iterator = paramMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, Object> entry = iterator.next();
            query.setParameter(entry.getKey(), entry.getValue());
        }

        query.setParameter("id", id);
        return query.executeUpdate();

    }

}
