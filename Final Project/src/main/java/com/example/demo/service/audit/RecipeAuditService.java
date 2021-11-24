package com.example.demo.service.audit;

import com.example.demo.model.Audit;
import com.example.demo.model.Recipe;
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
public class RecipeAuditService {
    private final UserHolder userHolder;
    private final IAuditService auditService;
    private final IUserService userService;

    public RecipeAuditService(UserHolder userHolder, IAuditService auditService, IUserService userService) {
        this.userHolder = userHolder;
        this.auditService = auditService;
        this.userService = userService;
    }

    @After("execution(* com.example.demo.service.RecipeService.save(..))")
    public void saveMethod(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            Recipe recipe = (Recipe) args[0];

            Audit audit = new Audit();
            audit.setDateCreate(recipe.getDateUpdate());
            audit.setDescription("Create Recipe " + recipe.getId());
            String login = userHolder.getAuthentication().getName();
            User userByLogin = userService.findUserByLogin(login);
            audit.setUser(userByLogin);
            audit.setEssenceName(EEssenceName.RECIPE);
            audit.setEssenceId(recipe.getId());
            auditService.save(audit);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @After("execution(* com.example.demo.service.RecipeService.update(..))")
    public void updateMethod(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            Recipe recipe = (Recipe) args[0];

            Audit audit = new Audit();
            audit.setDateCreate(recipe.getDateUpdate());
            audit.setDescription("Update Recipe " + recipe.getId());
            String login = userHolder.getAuthentication().getName();
            User userByLogin = userService.findUserByLogin(login);
            audit.setUser(userByLogin);
            audit.setEssenceName(EEssenceName.RECIPE);
            audit.setEssenceId(recipe.getId());
            auditService.save(audit);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @After("execution(* com.example.demo.service.RecipeService.delete(..))")
    public void deleteMethod(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            Long recipeId = (Long) args[0];

            Audit audit = new Audit();
            audit.setDateCreate(LocalDateTime.now());
            audit.setDescription("Delete Recipe " + recipeId);
            String login = userHolder.getAuthentication().getName();
            User userByLogin = userService.findUserByLogin(login);
            audit.setUser(userByLogin);
            audit.setEssenceName(EEssenceName.RECIPE);
            audit.setEssenceId(recipeId);
            auditService.save(audit);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
