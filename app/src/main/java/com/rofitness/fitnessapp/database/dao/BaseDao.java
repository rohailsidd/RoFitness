package com.rofitness.fitnessapp.database.dao;

import java.util.List;

public interface BaseDao<T> {
    void delete(T t);

    List<Long> insert(List<T> list);

    void insert(T t);

    void insert(T... tArr);

    void update(T t);
}
