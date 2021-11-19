package com.example.demo.service;

import com.example.demo.dao.api.IProfileDao;
import com.example.demo.dto.UserAuthDTO;
import com.example.demo.model.Profile;
import com.example.demo.model.User;
import com.example.demo.security.UserHolder;
import com.example.demo.service.api.IProfileService;
import com.example.demo.service.api.IUserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProfileService implements IProfileService {
    private final IProfileDao profileDao;
    private final UserHolder userHolder;
    private final IUserService userService;

    public ProfileService(IProfileDao profileDao, UserHolder userHolder, IUserService userService) {
        this.profileDao = profileDao;
        this.userHolder = userHolder;
        this.userService = userService;
    }

    @Override
    public Profile getById(long id) {
        return profileDao.findById(id).orElse(null);
    }

    @Override
    public Profile save(UserAuthDTO userAuthDTO) {
        Profile profile = new Profile();
        String loginUser = userHolder.getAuthentication().getName();
        User user = userService.findUserByLogin(loginUser);
        profile.setUser(user);
        profile.setHeight(userAuthDTO.getHeight());
        profile.setWeightTarget(userAuthDTO.getWeightTarget());
        profile.setWeightTarget(userAuthDTO.getWeightTarget());
        profile.setWeightFromWeightMeasurement(userAuthDTO.getWeightFromWeightMeasurement());
        profile.setDateOfBirthday(userAuthDTO.getDateOfBirthday());
        profile.setGender(userAuthDTO.getGender());
        profile.setLifestyle(userAuthDTO.getLifestyle());
        profile.setTarget(userAuthDTO.getTarget());
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
        if (updateProfile!=null){
            updateProfile.setDateOfBirthday(profile.getDateOfBirthday());
            updateProfile.setGender(profile.getGender());
            updateProfile.setHeight(profile.getHeight());
            String loginUser = userHolder.getAuthentication().getName();
            User user = userService.findUserByLogin(loginUser);
            updateProfile.setUser(user);
            updateProfile.setWeightTarget(profile.getWeightTarget());
            updateProfile.setWeightFromWeightMeasurement(profile.getWeightFromWeightMeasurement());
            updateProfile.setLifestyle(profile.getLifestyle());
            updateProfile.setTarget(profile.getTarget());
            return profileDao.save(updateProfile);
        } else {
            throw new IllegalArgumentException("Profile is not found");
        }
    }

    @Override
    public void delete(long id) {
        profileDao.deleteById(id);
    }
}
