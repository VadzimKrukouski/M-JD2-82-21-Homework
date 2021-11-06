package com.example.demo.service.api;

import com.example.demo.model.User;

import java.util.List;

public interface IUserService {
    User getById(long id);
    User save(User model);
    List<User> getAll();
    User update(User model, long id);
    void delete(long id);
}
