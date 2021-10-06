package ru.gb.hw03.task1.database.datasource;

import javax.sql.DataSource;

public interface DataSourceInterface {
    
    DataSource getMySQLDataSource();
    DataSource getPgSQLDataSource();
}
