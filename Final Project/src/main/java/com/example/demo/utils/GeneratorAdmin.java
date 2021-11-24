//package com.example.demo.utils;
//
//import com.example.demo.dao.api.IUserDao;
//import com.example.demo.model.User;
//import com.example.demo.model.api.ERole;
//import com.example.demo.model.api.EStatus;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//
//@Component
//public class GeneratorAdmin {
//    private final IUserDao userDao;
//    private final PasswordEncoder passwordEncoder;
//
//    public GeneratorAdmin(IUserDao userDao, PasswordEncoder passwordEncoder) {
//        this.userDao = userDao;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    public void generateAdmin(){
//        User user = new User();
//        user.setLogin("admin@mail.ru");
//        user.setName("Администратор Администратович");
//        user.setPassword(passwordEncoder.encode("111"));
//        user.setRole(ERole.ROLE_ADMIN);
//        user.setStatus(EStatus.ACTIVE);
//        LocalDateTime localDateTime = LocalDateTime.now();
//        user.setDateCreate(localDateTime);
//        user.setDateUpdate(localDateTime);
//        userDao.save(user);
//    }
//}
