package com.example.demo.service.audit;

import com.example.demo.model.Audit;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.model.api.EEssenceName;
import com.example.demo.security.UserHolder;
import com.example.demo.service.api.IAuditService;
import com.example.demo.service.api.IUserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class ProductAuditService {
    private final UserHolder userHolder;
    private final IAuditService auditService;
    private final IUserService userService;

    public ProductAuditService(UserHolder userHolder, IAuditService auditService, IUserService userService) {
        this.userHolder = userHolder;
        this.auditService = auditService;
        this.userService = userService;
    }

    @After("execution(* com.example.demo.service.ProductService.save(..))")
    public void saveMethod(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            Product product = (Product) args[0];

            Audit audit = new Audit();
            audit.setDateCreate(product.getDateUpdate());
            audit.setDescription("Create Product " + product.getId());
            String login = userHolder.getAuthentication().getName();
            User userByLogin = userService.findUserByLogin(login);
            audit.setUser(userByLogin);
            audit.setEssenceName(EEssenceName.PRODUCT);
            audit.setEssenceId(product.getId());
            auditService.save(audit);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @After("execution(* com.example.demo.service.ProductService.update(..))")
    public void updateMethod(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            Product product = (Product) args[0];

            Audit audit = new Audit();
            audit.setDateCreate(product.getDateUpdate());
            audit.setDescription("Update Product " + product.getId());
            String login = userHolder.getAuthentication().getName();
            User userByLogin = userService.findUserByLogin(login);
            audit.setUser(userByLogin);
            audit.setEssenceName(EEssenceName.PRODUCT);
            audit.setEssenceId(product.getId());
            auditService.save(audit);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @After("execution(* com.example.demo.service.ProductService.delete(..))")
    public void deleteMethod(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            Product product = (Product) args[0];

            Audit audit = new Audit();
            audit.setDateCreate(product.getDateUpdate());
            audit.setDescription("Delete Product " + product.getId());
            String login = userHolder.getAuthentication().getName();
            User userByLogin = userService.findUserByLogin(login);
            audit.setUser(userByLogin);
            audit.setEssenceName(EEssenceName.PRODUCT);
            audit.setEssenceId(product.getId());
            auditService.save(audit);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
