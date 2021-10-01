package ru.gb.hw02.task1.database.dao;

import ru.gb.hw02.task1.database.entities.User;

import java.util.List;

public interface UserDAO extends BaseDAO<Integer, User> {
    
    User selectOne(String login, String password);
}
