package ru.gb.hw02.task1.server;

import ru.gb.hw02.task1.database.entities.User;
import ru.gb.hw02.task1.database.services.UserService;

import java.util.Optional;

public class AuthService {

    public Optional<User> findUserByLoginAndPassword(String login, String password) {
        User user = UserService.getUserByLoginAndPassword(login, password);
        
        return Optional.ofNullable(user);
    }
    
    public int updateUserData(User user, String field, String value) {
    
        if ("login".equals(field)) {
            user.setLogin(value);
        }
    
        if ("name".equals(field)) {
            user.setName(value);
        }
    
        if ("email".equals(field)) {
            user.setEmail(value);
        }
    
        if ("age".equals(field)) {
            user.setAge(Integer.parseInt(value));
        }
    
        if ("sex".equals(field)) {
            user.setSex(value);
        }
        
        return UserService.setUserData(user);
    }
}
