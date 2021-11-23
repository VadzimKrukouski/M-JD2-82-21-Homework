package com.example.demo.controller;

import com.example.demo.model.Audit;
import com.example.demo.model.User;
import com.example.demo.model.api.ERole;
import com.example.demo.security.UserHolder;
import com.example.demo.service.api.IAuditService;
import com.example.demo.service.api.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/audits")
public class AuditController {
    private final IAuditService auditService;
    private final UserHolder userHolder;
    private final IUserService userService;

    public AuditController(IAuditService auditService, UserHolder userHolder, IUserService userService) {
        this.auditService = auditService;
        this.userHolder = userHolder;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Audit>> getAudits(){
        try {
            String login = userHolder.getAuthentication().getName();
            User user = userService.findUserByLogin(login);
            List <Audit> auditList;
            if (user.getRole().equals(ERole.ROLE_ADMIN)){
                auditList= auditService.getAll();
            } else {
                auditList= auditService.findAllByUserId(user.getId());
            }
            return new ResponseEntity<>(auditList, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
