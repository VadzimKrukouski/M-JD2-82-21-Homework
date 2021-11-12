package com.example.demo.service;

import com.example.demo.dao.api.IJournalDao;
import com.example.demo.model.Journal;
import com.example.demo.model.Profile;
import com.example.demo.service.api.IJournalService;
import com.example.demo.service.api.IProfileService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class JournalService implements IJournalService {
    private final IJournalDao journalDao;
    private final IProfileService profileService;

    public JournalService(IJournalDao journalDao, IProfileService profileService) {
        this.journalDao = journalDao;
        this.profileService = profileService;
    }

    @Override
    public Journal getById(long id) {
        return journalDao.findById(id).orElse(null);
    }

    @Override
    public Journal getByIdAndProfileId(long idProfile, long idFood) {
        return journalDao.findJournalByProfileIdAndId(idProfile, idFood);
    }

    @Override
    public Journal save(Journal journal, long idProfile) {
        LocalDateTime localDateTime = LocalDateTime.now();
        journal.setDateCreate(localDateTime);
        journal.setDateUpdate(localDateTime);
        Profile profile = profileService.getById(idProfile);
        journal.setProfile(profile);
        return journalDao.save(journal);
    }

    @Override
    public Page<Journal> getAll(long idProfile, Pageable pageable) {
        return journalDao.findAllByProfileId(idProfile,pageable);
    }

    @Override
    public Journal update(Journal journal, long idFood, long idProfile, LocalDateTime dateUpdate) {
        Journal updateJournal = getById(idFood);
        updateJournal.setRecipe(journal.getRecipe());
        updateJournal.setProduct(journal.getProduct());
        updateJournal.setWeight(journal.getWeight());
        updateJournal.setMealTime(journal.getMealTime());
        updateJournal.setDateUpdate(LocalDateTime.now());

        return save(updateJournal, idProfile);
    }

    @Override
    public void delete(long id) {
        journalDao.deleteById(id);
    }

}
