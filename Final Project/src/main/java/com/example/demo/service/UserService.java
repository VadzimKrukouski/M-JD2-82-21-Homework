package com.example.demo.service;

import com.example.demo.dao.api.IProfileDao;
import com.example.demo.dao.api.IUserDao;
import com.example.demo.model.User;
import com.example.demo.service.api.IAppService;
import com.example.demo.service.api.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    private final IUserDao userDao;

    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getById(long id) {
        return userDao.findById(id).orElse(null);
    }

    @Override
    public User save(User model) {
        return userDao.save(model);
    }

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }

    @Override
    public User update(User model, long id) {
        User updateUser = getById(id);
        updateUser.setLogin(model.getLogin());
        updateUser.setName(model.getName());
        updateUser.setPassword(model.getPassword());
        updateUser.setRole(model.getRole());
        return userDao.save(updateUser);
    }

    @Override
    public void delete(long id) {
        userDao.deleteById(id);
    }
}
