package ru.gb.hw03.task1.database.dao;

import ru.gb.hw03.task1.database.entities.User;

import java.util.List;

public interface UserDAO extends BaseDAO<Integer, User> {
    
    User selectOne(String login, String password);
}
