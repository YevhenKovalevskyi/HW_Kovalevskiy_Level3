package ru.gb.hw02.task1.database.dao;

import ru.gb.hw02.task1.database.entities.Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface BaseDAO<K, T extends Entity> {
    
    void insert(T t);
    int update(T t);
    void delete(T t);
    void delete(K id);
    List<T> selectAll();
    T selectOne(K id);
    
    default void close(Statement statement) throws SQLException {
        if (statement != null) {
            statement.close();
        }
    }
    
    default void close(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
