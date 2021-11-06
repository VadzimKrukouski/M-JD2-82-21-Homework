package com.example.demo.service.api;

import com.example.demo.model.Profile;

import java.util.List;

public interface IProfileService {
    Profile getById(long id);
    Profile save(Profile model);
    List<Profile> getAll();
    Profile update(Profile model, long id);
    void delete(long id);
}
