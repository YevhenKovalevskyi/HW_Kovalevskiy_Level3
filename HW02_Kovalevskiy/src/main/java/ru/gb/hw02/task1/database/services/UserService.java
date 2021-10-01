package ru.gb.hw02.task1.database.services;

import lombok.extern.slf4j.Slf4j;
import ru.gb.hw02.task1.database.dao.UserDAO;
import ru.gb.hw02.task1.database.dao.impl.UserDAOImpl;
import ru.gb.hw02.task1.database.entities.User;

import java.util.List;

@Slf4j
public class UserService {
    
    private static final UserDAO userDAO = new UserDAOImpl();
    
    public static User getUserById(Integer id) {
        log.info("Getting User by id.");
        return userDAO.selectOne(id);
    }
    
    public static User getUserByLoginAndPassword(String login, String password) {
        log.info("Getting User by login and password.");
        return userDAO.selectOne(login, password);
    }
    
    public static List<User> getUsers() {
        log.info("Getting Users.");
        return userDAO.selectAll();
    }
    
    public static int setUserData(User user) {
        log.info("Setting User data.");
        return userDAO.update(user);
    }
}
