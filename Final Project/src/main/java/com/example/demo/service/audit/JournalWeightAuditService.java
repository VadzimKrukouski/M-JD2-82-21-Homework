package com.example.demo.service.audit;

import com.example.demo.model.Audit;
import com.example.demo.model.JournalWeight;
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
public class JournalWeightAuditService {
    private final UserHolder userHolder;
    private final IAuditService auditService;
    private final IUserService userService;

    public JournalWeightAuditService(UserHolder userHolder, IAuditService auditService, IUserService userService) {
        this.userHolder = userHolder;
        this.auditService = auditService;
        this.userService = userService;
    }

    @After("execution(* com.example.demo.service.JournalWeightService.save(..))")
    public void saveMethod(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            JournalWeight journalWeight = (JournalWeight) args[0];

            Audit audit = new Audit();
            audit.setDateCreate(journalWeight.getDateUpdate());
            audit.setDescription("Create JournalWeight " + journalWeight.getId());
            String login = userHolder.getAuthentication().getName();
            User userByLogin = userService.findUserByLogin(login);
            audit.setUser(userByLogin);
            audit.setEssenceName(EEssenceName.JOURNAL_WEIGHT);
            audit.setEssenceId(journalWeight.getId());
            auditService.save(audit);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @After("execution(* com.example.demo.service.JournalWeightService.update(..))")
    public void updateMethod(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            JournalWeight journalWeight = (JournalWeight) args[0];

            Audit audit = new Audit();
            audit.setDateCreate(LocalDateTime.now());
            audit.setDescription("Update JournalWeight " + journalWeight.getId());
            String login = userHolder.getAuthentication().getName();
            User userByLogin = userService.findUserByLogin(login);
            audit.setUser(userByLogin);
            audit.setEssenceName(EEssenceName.JOURNAL_WEIGHT);
            audit.setEssenceId(journalWeight.getId());
            auditService.save(audit);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @After("execution(* com.example.demo.service.JournalWeightService.delete())")
    public void deleteMethod(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            Long journalWeight = (Long) args[0];

            Audit audit = new Audit();
            audit.setDateCreate(LocalDateTime.now());
            audit.setDescription("Delete JournalWeight " + journalWeight);
            String login = userHolder.getAuthentication().getName();
            User userByLogin = userService.findUserByLogin(login);
            audit.setUser(userByLogin);
            audit.setEssenceName(EEssenceName.JOURNAL_WEIGHT);
            audit.setEssenceId(journalWeight);
            auditService.save(audit);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
