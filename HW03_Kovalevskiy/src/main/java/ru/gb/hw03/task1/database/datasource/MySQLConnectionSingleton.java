package ru.gb.hw03.task1.database.datasource;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class MySQLConnectionSingleton {
    
    private static Connection instance;
    
    private MySQLConnectionSingleton() {
    
    }
    
    public static Connection getInstance() throws SQLException {
        if (instance == null || instance.isClosed()) {
            try {
                instance = new DataSourceFactory().getMySQLDataSource().getConnection();
                log.info("The new MySQLDataSource connection instance is created.");
            } catch (SQLException e) {
                log.error("MySQLDataSource getConnection error.");
                e.printStackTrace();
            }
        } else {
            log.info("Using existed MySQLDataSource connection instance.");
        }
        
        return instance;
    }
}
