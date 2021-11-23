package com.example.demo.service;

import com.example.demo.dao.api.IAuditDao;
import com.example.demo.model.Audit;
import com.example.demo.service.api.IAuditService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditService implements IAuditService {
    private final IAuditDao auditDao;

    public AuditService(IAuditDao auditDao) {
        this.auditDao = auditDao;
    }

    @Override
    public Audit getById(long id) {
        return auditDao.findById(id).orElse(null);
    }

    @Override
    public Audit save(Audit model) {
        return auditDao.save(model);
    }

    @Override
    public List<Audit> getAll() {
        return auditDao.findAll();
    }

    @Override
    public Audit update(Audit model, long id) {
        Audit updateAudit= getById(id);
        updateAudit.setDescription(model.getDescription());
        updateAudit.setUser(model.getUser());
        updateAudit.setEssenceName(model.getEssenceName());
        updateAudit.setEssenceId(model.getEssenceId());

        return save(updateAudit);
    }

    @Override
    public void delete(long id) {
        auditDao.deleteById(id);
    }

    @Override
    public List<Audit> findAllByUserId(long id) {
        return auditDao.findAllByUserId(id);
    }
}
