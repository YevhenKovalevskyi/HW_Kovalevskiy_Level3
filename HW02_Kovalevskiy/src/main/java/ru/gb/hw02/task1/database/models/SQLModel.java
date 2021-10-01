package ru.gb.hw02.task1.database.models;

import lombok.extern.slf4j.Slf4j;
import ru.gb.hw02.task1.database.datasource.MySQLConnectionSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

@Slf4j
public class SQLModel {
    
    private static final Connection connection = MySQLConnectionSingleton.getInstance();
    
    /*  Insert, Update, Delete  */
    public static int executeUpdate(String query) {
        int result = 0;
        
        try (Statement stmt = connection.createStatement()) {
            log.info("Created connection statement");
            
            stmt.executeUpdate(query);

            result = 1;
        } catch (SQLException e) {
            log.error("SQL connection createStatement error.");
            e.printStackTrace();
        }
        
        return result;
    }
    
    /*  Insert, Update, Delete  */
    public static int executeUpdateWithParams(String query, String[] params) {
        if (params.length == 0) {
            log.error("Empty query params array!");
            throw new IllegalArgumentException("Empty query params array!");
        }
        
        int result = 0;
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            log.info("Created connection statement");
            
            for (int i = 0; i < params.length; i++) {
                stmt.setString((i + 1), params[i]);
            }
    
            stmt.executeUpdate();
            
            result = 1;
        } catch (SQLException e) {
            log.error("SQL connection createStatement error.");
            e.printStackTrace();
        }
        
        return result;
    }
    
    /*  For Select  */
    public static ArrayList<HashMap<String, String>> execute(String query) {
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        
        try (
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query)
        ) {
            log.info("Created connection statement and statement resultSet.");
            
            ResultSetMetaData md = rs.getMetaData();
            log.info("Received resultSet metaData.");
            
            int columnCount = md.getColumnCount();
            
            while (rs.next()) {
                HashMap<String, String> items = new HashMap<>(columnCount);
                
                for (int i = 1; i <= columnCount; i++) {
                    items.put(md.getColumnLabel(i), rs.getString(i));
                }
                
                result.add(items);
            }
            
            log.info("Filled the result ArrayList with the HashMaps of the query data.");
        } catch (SQLException e) {
            log.error("SQL connection createStatement / executeQuery error.");
            e.printStackTrace();
        }
        
        return result;
    }
    
    /*  For Select  */
    public static ArrayList<HashMap<String, String>> executeWithParams(String query, String[] params) {
        if (params.length == 0) {
            log.error("Empty query params array!");
            throw new IllegalArgumentException("Empty query params array!");
        }
        
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            log.info("Created connection statement.");
            
            for (int i = 0; i < params.length; i++) {
                stmt.setString((i + 1), params[i]);
            }
            
            try (ResultSet rs = stmt.executeQuery()) {
                log.info("Created statement resultSet.");
    
                ResultSetMetaData md = rs.getMetaData();
                log.info("Received resultSet metaData.");
    
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
        } catch (SQLException e) {
            log.error("SQL connection createStatement / executeQuery error.");
            e.printStackTrace();
        }
        
        return result;
    }
}
