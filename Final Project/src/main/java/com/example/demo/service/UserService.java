package com.example.demo.service;

import com.example.demo.dao.api.IUserDao;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserAuthDTO;
import com.example.demo.model.User;
import com.example.demo.model.api.ERole;
import com.example.demo.model.api.EStatus;
import com.example.demo.service.api.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService implements IUserService {
    private final IUserDao userDao;
//    private final PasswordEncoder passwordEncoder;

    public UserService(IUserDao userDao) {
        this.userDao = userDao;
//        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getById(long id) {
        return userDao.findById(id).orElse(null);
    }

    @Override
    public User saveRegister(User user) {
        user.setRole(ERole.ROLE_USER);
        user.setStatus(EStatus.NOT_ACTIVE);
        LocalDateTime localDateTime = LocalDateTime.now().withNano(0);
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

        if (updateUser == null) {
            throw new IllegalArgumentException("User is not found by ID");
        }
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

    @Override
    public User findByLoginAndPassword(String login, String password) {
        User userByLogin = findUserByLogin(login);
        if (userByLogin != null) {
//            if (passwordEncoder.matches(password, userByLogin.getPassword())){
            return userByLogin;
//            }
        }
        return null;
    }

    @Override
    public void authUser(UserAuthDTO userAuthDTO) {
        User userByLogin = findUserByLogin(userAuthDTO.getLogin());
        if (userByLogin == null) {
            throw new IllegalArgumentException("User is not found by ID");
        }
        userByLogin.setStatus(EStatus.ACTIVE);
        userByLogin.setName(userAuthDTO.getName());
        userDao.save(userByLogin);
    }
}
