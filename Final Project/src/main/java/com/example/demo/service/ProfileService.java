package com.example.demo.service;

import com.example.demo.dao.api.IProfileDao;
import com.example.demo.dto.PersonalDataDTO;
import com.example.demo.model.Profile;
import com.example.demo.model.User;
import com.example.demo.security.UserHolder;
import com.example.demo.service.api.IProfileService;
import com.example.demo.service.api.IUserService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    public Profile save(PersonalDataDTO personalDataDTO) {
        Profile profile = new Profile();
        String loginUser = userHolder.getAuthentication().getName();
        User user = userService.findUserByLogin(loginUser);
        profile.setUser(user);
        profile.setHeight(personalDataDTO.getHeight());
        profile.setWeightTarget(personalDataDTO.getWeightTarget());
        profile.setWeightTarget(personalDataDTO.getWeightTarget());
        profile.setWeightFromWeightMeasurement(personalDataDTO.getWeightFromWeightMeasurement());

        long dateOfBirthdayInMilliseconds = personalDataDTO.getDateOfBirthday();
        LocalDate dateOfBirthdayLocalDate = Instant.ofEpochMilli(dateOfBirthdayInMilliseconds).atZone(ZoneId.systemDefault()).toLocalDate();
        profile.setDateOfBirthday(dateOfBirthdayLocalDate);

        profile.setGender(personalDataDTO.getGender());
        profile.setLifestyle(personalDataDTO.getLifestyle());
        profile.setTarget(personalDataDTO.getTarget());
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
