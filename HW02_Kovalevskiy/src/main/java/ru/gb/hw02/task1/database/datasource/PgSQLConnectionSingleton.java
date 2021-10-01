package ru.gb.hw02.task1.database.datasource;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class PgSQLConnectionSingleton {
    
    private static Connection instance;
    
    private PgSQLConnectionSingleton() {
    
    }
    
    public static Connection getInstance() {
        if (instance == null) {
            try {
                instance = new DataSourceFactory().getPgSQLDataSource().getConnection();
                log.info("The new PgSQLDataSource connection instance is created.");
            } catch (SQLException e) {
                log.error("PgSQLDataSource getConnection error.");
                e.printStackTrace();
            }
        } else {
            log.info("Using existed PgSQLDataSource connection instance.");
        }
        
        return instance;
    }
}
