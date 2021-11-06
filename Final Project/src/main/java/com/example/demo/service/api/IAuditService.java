package com.example.demo.service.api;

import com.example.demo.model.Audit;

import java.util.List;

public interface IAuditService {
    Audit getById(long id);
    Audit save(Audit model);
    List<Audit> getAll();
    Audit update(Audit model, long id);
    void delete(long id);
}

