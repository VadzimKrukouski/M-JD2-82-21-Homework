package com.example.demo.service;

import com.example.demo.dao.api.IProfileDao;
import com.example.demo.model.Profile;
import com.example.demo.service.api.IAppService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService implements IAppService<Profile> {
    private final IProfileDao profileDao;

    public ProfileService(IProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    @Override
    public Profile getById(long id) {
        return profileDao.findById(id).orElse(null);
    }

    @Override
    public Profile save(Profile model) {
        return profileDao.save(model);
    }

    @Override
    public List<Profile> getAll() {
        return profileDao.findAll();
    }

    @Override
    public Profile update(Profile model, long id) {
        Profile updateProfile = getById(id);
        updateProfile.setDateBirthday(model.getDateBirthday());
        updateProfile.setGender(model.getGender());
        updateProfile.setHeight(model.getHeight());
        updateProfile.setUser(model.getUser());
        updateProfile.setWeight(model.getWeight());
        updateProfile.setLifestyle(model.getLifestyle());
        updateProfile.setTarget(model.getTarget());
        return save(updateProfile);
    }

    @Override
    public void delete(long id) {
        profileDao.deleteById(id);
    }
}
