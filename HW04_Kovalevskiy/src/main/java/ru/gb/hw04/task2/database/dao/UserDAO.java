package ru.gb.hw04.task2.database.dao;

import ru.gb.hw04.task2.database.entities.User;

import java.util.List;

public interface UserDAO extends BaseDAO<Integer, User> {
    
    User selectOne(String login, String password);
}
