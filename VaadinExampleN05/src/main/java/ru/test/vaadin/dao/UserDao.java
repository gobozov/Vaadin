package ru.test.vaadin.dao;


import ru.test.vaadin.entity.User;

import java.util.List;

/**
 * Author: Georgy Gobozov
 * Date: 19.05.13
 */
public interface UserDao {

    void create(User user);
    void update(User user);
    User getById(Long userId);
    List<User> getAll();
    User getByLogin(String login);
    void delete(User user);

}
