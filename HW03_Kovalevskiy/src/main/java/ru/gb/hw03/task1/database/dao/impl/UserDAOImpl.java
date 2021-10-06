package ru.gb.hw03.task1.database.dao.impl;

import lombok.extern.slf4j.Slf4j;
import ru.gb.hw03.task1.database.dao.UserDAO;
import ru.gb.hw03.task1.database.entities.User;
import ru.gb.hw03.task1.database.models.SQLModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
public class UserDAOImpl implements UserDAO {
    
    @Override
    public void insert(User user) {
    
    }
    
    @Override
    public int update(User user) {
        int result = 0;
        
        try {
            String query =
                    " UPDATE users " +
                    " SET u_login = ?, u_name = ?, u_email = ?, u_age = ?, u_sex = ?, updated_at = NOW() " +
                    " WHERE u_id = ?";
            String[] params = {
                    user.getLogin(), user.getName(), user.getEmail(), "" + user.getAge(), user.getSex(), "" + user.getId()
            };
        
            result = SQLModel.executeUpdateWithParams(query, params);
            log.info("User data was updated.");
        } catch (IllegalArgumentException | SQLException e) {
            System.out.printf("[update] Query execution error: %s\n", e.getMessage());
        }
        
        return result;
    }
    
    @Override
    public void delete(User user) {
    
    }
    
    @Override
    public void delete(Integer id) {
    
    }
    
    @Override
    public User selectOne(Integer id) {
        User user = null;
        
        try {
            String query =
                    " SELECT u_id id, u_login login, u_name name, u_email email, u_age age, u_sex sex, updated_at" +
                    " FROM users WHERE u_id = ?";
            String[] params = { "" + id };
    
            ArrayList<HashMap<String, String>> result = SQLModel.executeWithParams(query, params);
            log.info("Received query result.");
    
            if (result.size() > 0) {
                HashMap<String, String> item = result.get(0);
        
                user = new User();
                log.info("The User object is created.");
        
                user.setId(Integer.parseInt(item.get("id")));
                user.setLogin(item.get("login"));
                user.setName(item.get("name"));
                user.setEmail(item.get("email"));
                user.setAge(Integer.parseInt(item.get("age")));
                user.setSex(item.get("sex"));
                user.setUpdated_at(item.get("updated_at"));
                log.info("The fields are set based on the query result.");
            }
        } catch (IllegalArgumentException | SQLException e) {
            System.out.printf("[selectOne] Query execution error: %s\n", e.getMessage());
        }
        
        return user;
    }
    
    @Override
    public User selectOne(String login, String password) {
        User user = null;
        
        try {
            String query =
                    " SELECT u_id id, u_login login, u_name name, u_email email, u_age age, u_sex sex, updated_at" +
                    " FROM users WHERE u_login = ? AND u_password = old_password(?)";
            String[] params = { login, password };

            ArrayList<HashMap<String, String>> result = SQLModel.executeWithParams(query, params);
            log.info("Received query result.");
    
            if (result.size() > 0) {
                HashMap<String, String> item = result.get(0);
        
                user = new User();
                log.info("The User object is created.");
        
                user.setId(Integer.parseInt(item.get("id")));
                user.setLogin(item.get("login"));
                user.setName(item.get("name"));
                user.setEmail(item.get("email"));
                user.setAge(Integer.parseInt(item.get("age")));
                user.setSex(item.get("sex"));
                user.setUpdated_at(item.get("updated_at"));
                log.info("The fields are set based on the query result.");
            }
        } catch (IllegalArgumentException | SQLException e) {
            System.out.printf("[selectOne] Query execution error: %s\n", e.getMessage());
        }
        
        return user;
    }
    
    @Override
    public List<User> selectAll() {
        List<User> users = new ArrayList<>();
    
        try {
            String query =
                    " SELECT u_id id, u_login login, u_name name, u_email email, u_age age, u_sex sex, updated_at" +
                    " FROM users ORDER BY created_at DESC";
            
            ArrayList<HashMap<String, String>> result = SQLModel.execute(query);
            log.info("Received query result.");
            
            if (result.size() > 0) {
                result.forEach(item -> {
                    User user = new User();
                    log.info("The User object is created.");
        
                    user.setId(Integer.parseInt(item.get("id")));
                    user.setLogin(item.get("login"));
                    user.setName(item.get("name"));
                    user.setEmail(item.get("email"));
                    user.setAge(Integer.parseInt(item.get("age")));
                    user.setSex(item.get("sex"));
                    user.setUpdated_at(item.get("updated_at"));
                    log.info("The fields are set based on the query result.");
                    
                    users.add(user);
                    log.info("The User object is added to ArrayList.");
                });
            }
        } catch (SQLException e) {
            System.out.printf("[selectAll] Query execution error: %s\n", e.getMessage());
        }
        
        return users;
    }
}
