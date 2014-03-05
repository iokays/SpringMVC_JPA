package com.iokays.homepage.repository.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.iokays.homepage.domain.HomePage;
import com.iokays.homepage.repository.plus.HomePageRepositoryPlus;

public class HomePageRepositoryImpl implements HomePageRepositoryPlus {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Integer deleteById(Serializable id) {
        Query query = entityManager.createQuery("delete from HomePage t1 where t1.id =:id");
        return query.setParameter("id", id).executeUpdate();
    }

    @Override
    public Integer update(Serializable id, Map<String, Object> map) throws Exception {

        String jpql = "update HomePage set";
        Field[] fields = HomePage.class.getDeclaredFields();

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
