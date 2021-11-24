package com.example.demo.service.audit;

import com.example.demo.model.Audit;
import com.example.demo.model.JournalActive;
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
public class JournalActiveAuditService {
    private final UserHolder userHolder;
    private final IAuditService auditService;
    private final IUserService userService;

    public JournalActiveAuditService(UserHolder userHolder, IAuditService auditService, IUserService userService) {
        this.userHolder = userHolder;
        this.auditService = auditService;
        this.userService = userService;
    }

    @After("execution(* com.example.demo.service.JournalActiveService.save())")
    public void saveMethod(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            JournalActive journalActive = (JournalActive) args[0];

            Audit audit = new Audit();
            audit.setDateCreate(journalActive.getDateUpdate());
            audit.setDescription("Create JournalActive " + journalActive.getId());
            String login = userHolder.getAuthentication().getName();
            User userByLogin = userService.findUserByLogin(login);
            audit.setUser(userByLogin);
            audit.setEssenceName(EEssenceName.JOURNAL_ACTIVE);
            audit.setEssenceId(journalActive.getId());
            auditService.save(audit);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @After("execution(* com.example.demo.service.JournalActiveService.update())")
    public void updateMethod(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            JournalActive journalActive = (JournalActive) args[0];

            Audit audit = new Audit();
            audit.setDateCreate(LocalDateTime.now());
            audit.setDescription("Update JournalActive " + journalActive.getId());
            String login = userHolder.getAuthentication().getName();
            User userByLogin = userService.findUserByLogin(login);
            audit.setUser(userByLogin);
            audit.setEssenceName(EEssenceName.JOURNAL_ACTIVE);
            audit.setEssenceId(journalActive.getId());
            auditService.save(audit);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @After("execution(* com.example.demo.service.JournalActiveService.delete())")
    public void deleteMethod(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            Long journalActiveId = (Long) args[0];

            Audit audit = new Audit();
            audit.setDateCreate(LocalDateTime.now());
            audit.setDescription("Delete JournalActive " + journalActiveId);
            String login = userHolder.getAuthentication().getName();
            User userByLogin = userService.findUserByLogin(login);
            audit.setUser(userByLogin);
            audit.setEssenceName(EEssenceName.JOURNAL_ACTIVE);
            audit.setEssenceId(journalActiveId);
            auditService.save(audit);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
