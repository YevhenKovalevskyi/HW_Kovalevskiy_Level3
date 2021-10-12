package ru.gb.hw04.task2.database.datasource;

import javax.sql.DataSource;

public interface DataSourceInterface {
    
    DataSource getMySQLDataSource();
    DataSource getPgSQLDataSource();
}
