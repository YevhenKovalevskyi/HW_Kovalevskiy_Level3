package ru.gb.hw02.task1.database.models;

import lombok.extern.slf4j.Slf4j;
import ru.gb.hw02.task1.database.datasource.MySQLConnectionSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

@Slf4j
public class SQLModel {
    
    /*  Insert, Update, Delete  */
    public static int executeUpdate(String query) throws SQLException {
        int result = 0;
        
        try (
                Connection connection = MySQLConnectionSingleton.getInstance();
                Statement stmt = connection.createStatement()
        ) {
            try {
                log.info("Created Connection and PreparedStatement.");
                connection.setAutoCommit(false);
                
                result = stmt.executeUpdate(query);
                
                connection.commit();
                log.error("SQL query executed.");
            } catch (SQLException e) {
                connection.rollback();
                log.error("SQL query execution error. Transaction rolled back.");
                e.printStackTrace();
            }
        }
        
        return result;
    }
    
    /*  Insert, Update, Delete  */
    public static int executeUpdateWithParams(String query, String[] params) throws SQLException {
        if (params.length == 0) {
            log.error("Empty query params array!");
            throw new IllegalArgumentException("Empty query params array!");
        }
        
        int result = 0;
        
        try (
                Connection connection = MySQLConnectionSingleton.getInstance();
                PreparedStatement stmt = connection.prepareStatement(query)
        ) {
            try {
                log.info("Created Connection and PreparedStatement.");
                connection.setAutoCommit(false);
                
                for (int i = 0; i < params.length; i++) {
                    stmt.setString((i + 1), params[i]);
                }
        
                result = stmt.executeUpdate();
                
                connection.commit();
                log.error("SQL query executed.");
            } catch (SQLException e) {
                connection.rollback();
                log.error("SQL query execution error. Transaction rolled back.");
                e.printStackTrace();
            }
        }
        
        return result;
    }
    
    /*  For Select  */
    public static ArrayList<HashMap<String, String>> execute(String query) throws SQLException {
        ArrayList<HashMap<String, String>> result = new ArrayList<>();

        try (
                Connection connection = MySQLConnectionSingleton.getInstance();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query)
        ) {
            log.info("Created Connection and Statement and ResultSet.");
            log.error("SQL query executed.");
            
            ResultSetMetaData md = rs.getMetaData();
            log.info("Received ResultSet metaData.");
            
            int columnCount = md.getColumnCount();
            
            while (rs.next()) {
                HashMap<String, String> items = new HashMap<>(columnCount);
                
                for (int i = 1; i <= columnCount; i++) {
                    items.put(md.getColumnLabel(i), rs.getString(i));
                }
                
                result.add(items);
            }
            
            log.info("Filled the result ArrayList with the HashMaps of the query data.");
        }
        
        return result;
    }
    
    /*  For Select  */
    public static ArrayList<HashMap<String, String>> executeWithParams(String query, String[] params) throws SQLException {
        if (params.length == 0) {
            log.error("Empty query params array!");
            throw new IllegalArgumentException("Empty query params array!");
        }
        
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        
        try (
                Connection connection = MySQLConnectionSingleton.getInstance();
                PreparedStatement stmt = connection.prepareStatement(query)
        ) {
            log.info("Created Connection and PreparedStatement.");
            
            for (int i = 0; i < params.length; i++) {
                stmt.setString((i + 1), params[i]);
            }
            
            try (ResultSet rs = stmt.executeQuery()) {
                log.error("SQL query executed.");
                log.info("Created ResultSet.");
    
                ResultSetMetaData md = rs.getMetaData();
                log.info("Received ResultSet metaData.");
    
                int columnCount = md.getColumnCount();
    
                while (rs.next()) {
                    HashMap<String, String> items = new HashMap<>(columnCount);
        
                    for (int i = 1; i <= columnCount; i++) {
                        items.put(md.getColumnLabel(i), rs.getString(i));
                    }
        
                    result.add(items);
                }
    
                log.info("Filled the result ArrayList with the HashMaps of the query data.");
            }
        }
        
        return result;
    }
}
