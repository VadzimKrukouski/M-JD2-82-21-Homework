package com.example.demo.service.api;

import com.example.demo.model.User;

import java.util.List;

public interface IUserService {
    User getById(long id);
    User saveNewUser(User user);
    User checkAndUpdateDataUser(User user);
    List<User> getAll();
    User update(User user, long id);
    void delete(long id);
    User findUserByLogin(String login);
    User findByLoginAndPassword(String login, String password);
}
