package com.example.demo.service;

import com.example.demo.dao.api.IJournalActiveDao;
import com.example.demo.model.JournalActive;
import com.example.demo.model.Profile;
import com.example.demo.service.api.IJournalActiveService;
import com.example.demo.service.api.IProfileService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JournalActiveService implements IJournalActiveService {
    private final IJournalActiveDao journalActiveDao;
    private final IProfileService profileService;

    public JournalActiveService(IJournalActiveDao journalActiveDao, IProfileService profileService) {
        this.journalActiveDao = journalActiveDao;
        this.profileService = profileService;
    }

    @Override
    public Page<JournalActive> findAllByProfileIdAndDateCreateBetween(long idProfile, LocalDateTime dateStart, LocalDateTime dateEnd, Pageable pageable) {
        return journalActiveDao.findAllByProfileIdAndDateCreateBetween(idProfile, dateStart, dateEnd, pageable);
    }

    @Override
    public JournalActive getById(long id) {
        return journalActiveDao.findById(id).orElse(null);
    }

    @Override
    public JournalActive findAllByProfileIdAndId(long idProfile, long idWorkout) {
        return journalActiveDao.findAllByProfileIdAndId(idProfile, idWorkout);
    }

    @Override
    public JournalActive save(com.example.demo.model.JournalActive journalActive, long idProfile) {
        Profile profile = profileService.getById(idProfile);
        journalActive.setProfile(profile);
        LocalDateTime localDateTime = LocalDateTime.now().withNano(0);
        journalActive.setDateCreate(localDateTime);
        journalActive.setDateUpdate(localDateTime);
        return journalActiveDao.save(journalActive);
    }

    @Override
    public List<JournalActive> getAll() {
        return journalActiveDao.findAll();
    }

    @Override
    public JournalActive update(JournalActive journalActive, long idWorkout, long idProfile, LocalDateTime date) {
        JournalActive updateJournalActive = getById(idWorkout);

        if (updateJournalActive == null) {
            throw new IllegalArgumentException("JournalActive is not found by ID");
        }
        updateJournalActive.setName(journalActive.getName());
        Profile profile = profileService.getById(idProfile);
        updateJournalActive.setProfile(profile);
        updateJournalActive.setCalories(journalActive.getCalories());

        if (updateJournalActive.getDateUpdate().isEqual(date)) {
            return journalActiveDao.save(updateJournalActive);
        } else {
            throw new IllegalArgumentException("Optimistic lock. JournalActive already updated");
        }
    }

    @Override
    public void delete(long id, LocalDateTime date) {
        JournalActive journalActive = getById(id);
        if (journalActive == null) {
            throw new IllegalArgumentException("JournalFood is not found by ID");
        }
        journalActiveDao.deleteById(id);

    }
}
