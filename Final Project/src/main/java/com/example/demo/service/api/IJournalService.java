package com.example.demo.service.api;

import com.example.demo.model.Journal;

import java.util.List;

public interface IJournalService {
    Journal getById(long id);
    Journal save(Journal model);
    List<Journal> getAll();
    Journal update(Journal model, long id);
    void delete(long id);
}
