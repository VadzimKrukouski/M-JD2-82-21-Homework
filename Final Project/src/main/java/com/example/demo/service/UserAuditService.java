package com.example.demo.service;

import com.example.demo.model.Audit;
import com.example.demo.model.User;
import com.example.demo.model.api.EEssenceName;
import com.example.demo.security.UserHolder;
import com.example.demo.service.api.IAuditService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class UserAuditService {
    private final UserHolder userHolder;
    private final IAuditService auditService;

    public UserAuditService(UserHolder userHolder, IAuditService auditService) {
        this.userHolder = userHolder;
        this.auditService = auditService;
    }

    @After("execution(* com.example.demo.service.UserService.save(..))")
    public void saveMethod(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            User user = (User) args[0];

            Audit audit = new Audit();
            audit.setDateCreate(user.getDateUpdate());
            audit.setDescription("Create User " + user.getId());
//            audit.setUser(userHolder.getAuthentication().);
            audit.setEssenceName(EEssenceName.USER);
            audit.setEssenceId(user.getId());
            auditService.save(audit);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @After("execution(* com.example.demo.service.UserService.update(..))")
    public void updateMethod(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            User user = (User) args[0];

            Audit audit = new Audit();
            audit.setDateCreate(user.getDateUpdate());
            audit.setDescription("Update User " + user.getId());
//            audit.setUser(userHolder.getAuthentication());
            audit.setEssenceName(EEssenceName.USER);
            audit.setEssenceId(user.getId());
            auditService.save(audit);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }




}
