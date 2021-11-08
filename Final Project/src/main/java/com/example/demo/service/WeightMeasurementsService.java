package com.example.demo.service;

import com.example.demo.dao.api.IWeightMeasurementsDao;
import com.example.demo.model.Profile;
import com.example.demo.model.WeightMeasurements;
import com.example.demo.service.api.IProfileService;
import com.example.demo.service.api.IWeightMeasurementsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeightMeasurementsService implements IWeightMeasurementsService {
    private final IWeightMeasurementsDao weightMeasurements;
    private final IProfileService profileService;

    public WeightMeasurementsService(IWeightMeasurementsDao weightMeasurements, IProfileService profileService) {
        this.weightMeasurements = weightMeasurements;
        this.profileService = profileService;
    }

    @Override
    public WeightMeasurements getById(long id) {
        return weightMeasurements.findById(id).orElse(null);
    }

    @Override
    public WeightMeasurements save(WeightMeasurements model, long idProfile) {
        Profile profile = profileService.getById(idProfile);
        model.setProfile(profile);
        return weightMeasurements.save(model);
    }

    @Override
    public List<WeightMeasurements> getAll() {
        return weightMeasurements.findAll();
    }

    @Override
    public WeightMeasurements update(WeightMeasurements model, long id, long idProfile) {
        WeightMeasurements updateWeightMeasurement = getById(id);
        updateWeightMeasurement.setProfile(model.getProfile());
        updateWeightMeasurement.setValue(model.getValue());
        return save(updateWeightMeasurement, idProfile);
    }

    @Override
    public void delete(long id) {
        weightMeasurements.deleteById(id);
    }
}
