package com.example.demo.service.api;

import com.example.demo.dto.PersonalDataDTO;
import com.example.demo.model.Profile;

import java.util.List;

public interface IProfileService {
    Profile getById(long id);
    Profile save(PersonalDataDTO personalDataDTO);
    List<Profile> getAll();
    Profile update(Profile profile, long id);
    void delete(long id);
}
