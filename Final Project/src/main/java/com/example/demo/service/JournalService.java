package com.example.demo.service;

import com.example.demo.dao.api.IJournalDao;
import com.example.demo.model.Journal;
import com.example.demo.service.api.IAppService;
import com.example.demo.service.api.IJournalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalService implements IJournalService {
    private final IJournalDao journalDao;

    public JournalService(IJournalDao journalDao) {
        this.journalDao = journalDao;
    }

    @Override
    public Journal getById(long id) {
        return journalDao.findById(id).orElse(null);
    }

    @Override
    public Journal save(Journal model) {
        return journalDao.save(model);
    }

    @Override
    public List<Journal> getAll() {
        return journalDao.findAll();
    }

    @Override
    public Journal update(Journal model, long id) {
        Journal updateJournal = getById(id);
        updateJournal.setRecipe(model.getRecipe());
        updateJournal.setProduct(model.getProduct());
        updateJournal.setWeight(model.getWeight());
        updateJournal.setMealTime(model.getMealTime());
        updateJournal.setProfile(model.getProfile());
        return save(updateJournal);
    }

    @Override
    public void delete(long id) {
        journalDao.deleteById(id);
    }

}
