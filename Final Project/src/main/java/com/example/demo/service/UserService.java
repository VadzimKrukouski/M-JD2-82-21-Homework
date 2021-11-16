package com.example.demo.service;

import com.example.demo.dao.api.IProfileDao;
import com.example.demo.dao.api.IUserDao;
import com.example.demo.model.User;
import com.example.demo.service.api.IAppService;
import com.example.demo.service.api.IUserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public User save(User user) {
        LocalDateTime localDateTime = LocalDateTime.now();
        user.setDateCreate(localDateTime);
        user.setDateUpdate(localDateTime);
        return userDao.save(user);
    }

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }

    @Override
    public User update(User user, long id) {
        User updateUser = getById(id);
        updateUser.setLogin(user.getLogin());
        updateUser.setName(user.getName());
        updateUser.setPassword(user.getPassword());
        updateUser.setRole(user.getRole());
        return userDao.save(updateUser);
    }

    @Override
    public void delete(long id) {
        userDao.deleteById(id);
    }

    @Override
    public User findUserByLogin(String login) {
        return userDao.findUserByLogin(login);
    }
}
