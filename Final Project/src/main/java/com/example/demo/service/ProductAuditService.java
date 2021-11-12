package com.example.demo.service;

import com.example.demo.model.Audit;
import com.example.demo.model.Product;
import com.example.demo.security.UserHolder;
import com.example.demo.service.api.IAuditService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class ProductAuditService {
    private final UserHolder userHolder;
    private final IAuditService auditService;

    public ProductAuditService(UserHolder userHolder, IAuditService auditService) {
        this.userHolder = userHolder;
        this.auditService = auditService;
    }

    @After("execution(* com.example.demo.service.ProductService.save(..))")
    public void saveMethod(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            Product product = (Product) args[0];

            Audit audit = new Audit();
            audit.setDateCreate(product.getDateUpdate());
            audit.setDescription("Create Product " + product.getId());
//            audit.setUser(userHolder.getAuthentication());
            audit.setEssenceName("Product");
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
//            audit.setUser(userHolder.getAuthentication());
            audit.setEssenceName("Product");
            audit.setEssenceId(product.getId());
            auditService.save(audit);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


}
