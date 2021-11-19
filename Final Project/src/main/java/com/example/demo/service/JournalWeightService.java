package com.example.demo.service;

import com.example.demo.dao.api.IJournalWeightDao;
import com.example.demo.model.JournalWeight;
import com.example.demo.model.Profile;
import com.example.demo.service.api.IJournalWeightService;
import com.example.demo.service.api.IProfileService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JournalWeightService implements IJournalWeightService {
    private final IJournalWeightDao journalWeightDao;
    private final IProfileService profileService;

    public JournalWeightService(IJournalWeightDao journalWeightDao, IProfileService profileService) {
        this.journalWeightDao = journalWeightDao;
        this.profileService = profileService;
    }

    @Override
    public Page<JournalWeight> findAllByProfileIdAndDateCreateBetween(long idProfile, LocalDateTime dateStart, LocalDateTime dateEnd, Pageable pageable) {
        return journalWeightDao.findAllByProfileIdAndDateCreateBetween(idProfile, dateStart, dateEnd, pageable);
    }

    @Override
    public JournalWeight getById(long id) {
        return journalWeightDao.findById(id).orElse(null);
    }


    @Override
    public JournalWeight getByIdProfileAndId(long idProfile, long idWeight) {
        return journalWeightDao.findAllByProfileIdAndId(idProfile, idWeight);
    }

    @Override
    public JournalWeight save(JournalWeight journalWeight, long idProfile) {
        Profile profile = profileService.getById(idProfile);
        journalWeight.setProfile(profile);
        LocalDateTime localDateTime = LocalDateTime.now().withNano(0);
        journalWeight.setDateCreate(localDateTime);
        journalWeight.setDateUpdate(localDateTime);
        return journalWeightDao.save(journalWeight);
    }

    @Override
    public List<JournalWeight> getAll() {
        return journalWeightDao.findAll();
    }

    @Override
    public JournalWeight update(JournalWeight journalWeight, long id, long idProfile, LocalDateTime date) {
        JournalWeight updateJournalWeight = getById(id);

        if (updateJournalWeight == null) {
            throw new IllegalArgumentException("JournalWeight is not found by ID");
        }
        Profile profile = profileService.getById(idProfile);
        updateJournalWeight.setProfile(profile);
        updateJournalWeight.setWeight(journalWeight.getWeight());

        if (updateJournalWeight.getDateUpdate().isEqual(date)) {
            return journalWeightDao.save(updateJournalWeight);
        } else {
            throw new IllegalArgumentException("Optimistic lock. JournalWeight already updated");
        }
    }

    @Override
    public void delete(long id, LocalDateTime date) {
        JournalWeight journalWeight = getById(id);
        if (journalWeight == null) {
            throw new IllegalArgumentException("JournalFood is not found by ID");
        }
        journalWeightDao.deleteById(id);
    }
}
