package com.example.demo.service.api;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserAuthDTO;
import com.example.demo.model.User;

import java.util.List;

public interface IUserService {
    User getById(long id);
    User saveRegister(User user);
    void authUser(UserAuthDTO userAuthDTO);
    List<User> getAll();
    User update(User user, long id);
    void delete(long id);
    User findUserByLogin(String login);
    User findByLoginAndPassword(String login, String password);
}
