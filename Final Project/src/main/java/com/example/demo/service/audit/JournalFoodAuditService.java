package com.example.demo.service.audit;

import com.example.demo.model.Audit;
import com.example.demo.model.JournalFood;
import com.example.demo.model.User;
import com.example.demo.model.api.EEssenceName;
import com.example.demo.security.UserHolder;
import com.example.demo.service.api.IAuditService;
import com.example.demo.service.api.IUserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Aspect
@Service
public class JournalFoodAuditService {
    private final UserHolder userHolder;
    private final IAuditService auditService;
    private final IUserService userService;

    public JournalFoodAuditService(UserHolder userHolder, IAuditService auditService, IUserService userService) {
        this.userHolder = userHolder;
        this.auditService = auditService;
        this.userService = userService;
    }

    @After("execution(* com.example.demo.service.JournalFoodService.save(..))")
    public void saveMethod(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            JournalFood journalFood = (JournalFood) args[0];

            Audit audit = new Audit();
            audit.setDateCreate(journalFood.getDateUpdate());
            audit.setDescription("Create JournalFood " + journalFood.getId());
            String login = userHolder.getAuthentication().getName();
            User userByLogin = userService.findUserByLogin(login);
            audit.setUser(userByLogin);
            audit.setEssenceName(EEssenceName.JOURNAL_FOOD);
            audit.setEssenceId(journalFood.getId());
            auditService.save(audit);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @After("execution(* com.example.demo.service.JournalFoodService.update(..))")
    public void updateMethod(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            JournalFood journalFood = (JournalFood) args[0];

            Audit audit = new Audit();
            audit.setDateCreate(LocalDateTime.now());
            audit.setDescription("Update JournalFood " + journalFood.getId());
            String login = userHolder.getAuthentication().getName();
            User userByLogin = userService.findUserByLogin(login);
            audit.setUser(userByLogin);
            audit.setEssenceName(EEssenceName.JOURNAL_FOOD);
            audit.setEssenceId(journalFood.getId());
            auditService.save(audit);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @After("execution(* com.example.demo.service.JournalFoodService.delete(..))")
    public void deleteMethod(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            Long journalFoodId = (Long) args[0];

                        Audit audit = new Audit();
            audit.setDateCreate(LocalDateTime.now());
            audit.setDescription("Delete JournalFood " + journalFoodId);
            String login = userHolder.getAuthentication().getName();
            User userByLogin = userService.findUserByLogin(login);
            audit.setUser(userByLogin);
            audit.setEssenceName(EEssenceName.JOURNAL_FOOD);
            audit.setEssenceId(journalFoodId);
            auditService.save(audit);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
