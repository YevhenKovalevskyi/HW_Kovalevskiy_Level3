package ru.gb.hw04.task2.database.dao;

import ru.gb.hw04.task2.database.entities.Entity;

import java.util.List;

public interface BaseDAO<K, T extends Entity> {
    
    void insert(T t);
    int update(T t);
    void delete(T t);
    void delete(K id);
    List<T> selectAll();
    T selectOne(K id);
}
