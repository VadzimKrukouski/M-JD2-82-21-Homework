package com.example.demo.service;

import com.example.demo.dao.api.IProfileDao;
import com.example.demo.model.Profile;
import com.example.demo.service.api.IProfileService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProfileService implements IProfileService {
    private final IProfileDao profileDao;

    public ProfileService(IProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    @Override
    public Profile getById(long id) {
        return profileDao.findById(id).orElse(null);
    }

    @Override
    public Profile save(Profile profile) {
        LocalDateTime localDateTime = LocalDateTime.now();
        profile.setDateCreate(localDateTime);
        profile.setDateUpdate(localDateTime);
        return profileDao.save(profile);
    }

    @Override
    public List<Profile> getAll() {
        return profileDao.findAll();
    }

    @Override
    public Profile update(Profile profile, long id) {
        Profile updateProfile = getById(id);
        updateProfile.setDateOfBirthday(profile.getDateOfBirthday());
        updateProfile.setGender(profile.getGender());
        updateProfile.setHeight(profile.getHeight());
        updateProfile.setUser(profile.getUser());
        updateProfile.setWeightTarget(profile.getWeightTarget());
        updateProfile.setLifestyle(profile.getLifestyle());
        updateProfile.setTarget(profile.getTarget());
        return profileDao.save(updateProfile);
    }

    @Override
    public void delete(long id) {
        profileDao.deleteById(id);
    }
}
