package com.example.demo.service;

import com.example.demo.dao.api.IJournalWeightDao;
import com.example.demo.model.JournalWeight;
import com.example.demo.model.Profile;
import com.example.demo.security.CheckUserAndProfile;
import com.example.demo.service.api.IJournalWeightService;
import com.example.demo.service.api.IProfileService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("DuplicatedCode")
@Service
public class JournalWeightService implements IJournalWeightService {
    private final IJournalWeightDao journalWeightDao;
    private final IProfileService profileService;
    private final CheckUserAndProfile checkUserAndProfile;

    public JournalWeightService(IJournalWeightDao journalWeightDao, IProfileService profileService, CheckUserAndProfile checkUserAndProfile) {
        this.journalWeightDao = journalWeightDao;
        this.profileService = profileService;
        this.checkUserAndProfile = checkUserAndProfile;
    }

    @Override
    public Page<JournalWeight> findAllByProfileIdAndDateCreateBetween(long idProfile, LocalDateTime dateStart, LocalDateTime dateEnd, Pageable pageable) {
        if (checkUserAndProfile.checkProfileId(idProfile)) {
            return journalWeightDao.findAllByProfileIdAndDateCreateBetween(idProfile, dateStart, dateEnd, pageable);
        } else {
            throw new IllegalArgumentException("No access rights to this profile");
        }
    }

    @Override
    public JournalWeight getById(long id) {
        return journalWeightDao.findById(id).orElse(null);
    }


    @Override
    public JournalWeight getByIdProfileAndId(long idProfile, long idWeight) {
        if (checkUserAndProfile.checkProfileId(idProfile)) {
            return journalWeightDao.findAllByProfileIdAndId(idProfile, idWeight);
        } else {
            throw new IllegalArgumentException("No access rights to this profile");
        }
    }

    @Override
    public JournalWeight save(JournalWeight journalWeight, long idProfile) {
        if (checkUserAndProfile.checkProfileId(idProfile)) {
            Profile profile = profileService.getById(idProfile);
            journalWeight.setProfile(profile);
            LocalDateTime localDateTime = LocalDateTime.now().withNano(0);
            journalWeight.setDateCreate(localDateTime);
            journalWeight.setDateUpdate(localDateTime);
            return journalWeightDao.save(journalWeight);
        } else {
            throw new IllegalArgumentException("No access rights to this profile");
        }

    }

    @Override
    public List<JournalWeight> getAll() {
        return journalWeightDao.findAll();
    }

    @Override
    public JournalWeight update(JournalWeight journalWeight, long id, long idProfile, LocalDateTime date) {
        if (checkUserAndProfile.checkProfileId(idProfile)) {
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
        } else {
            throw new IllegalArgumentException("No access rights to this profile");
        }
    }

    @Override
    public void delete(long id, LocalDateTime date, long idProfile) {
        if (checkUserAndProfile.checkProfileId(idProfile)) {
            JournalWeight journalWeight = getById(id);
            if (journalWeight == null) {
                throw new IllegalArgumentException("JournalFood is not found by ID");
            }
            if (journalWeight.getDateUpdate().isEqual(date)) {
                journalWeightDao.deleteById(id);
            } else {
                throw new IllegalArgumentException("Optimistic lock. JournalWeight already updated");
            }
        } else {
            throw new IllegalArgumentException("No access rights to this profile");
        }
    }
}
