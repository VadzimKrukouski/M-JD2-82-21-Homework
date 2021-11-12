package com.example.demo.service;

import com.example.demo.dao.api.IWeightMeasurementsDao;
import com.example.demo.model.Profile;
import com.example.demo.model.WeightMeasurements;
import com.example.demo.service.api.IProfileService;
import com.example.demo.service.api.IWeightMeasurementsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeightMeasurementsService implements IWeightMeasurementsService {
    private final IWeightMeasurementsDao weightMeasurementsDao;
    private final IProfileService profileService;

    public WeightMeasurementsService(IWeightMeasurementsDao weightMeasurementsDao, IProfileService profileService) {
        this.weightMeasurementsDao = weightMeasurementsDao;
        this.profileService = profileService;
    }

    @Override
    public WeightMeasurements getById(long id) {
        return weightMeasurementsDao.findById(id).orElse(null);
    }


    @Override
    public WeightMeasurements getByIdProfileAndId(long idProfile, long idWeight) {
        return weightMeasurementsDao.findAllByProfileIdAndId(idProfile, idWeight);
    }

    @Override
    public WeightMeasurements save(WeightMeasurements weightMeasurements, long idProfile) {
        Profile profile = profileService.getById(idProfile);
        weightMeasurements.setProfile(profile);
        LocalDateTime localDateTime = LocalDateTime.now();
        weightMeasurements.setDateCreate(localDateTime);
        weightMeasurements.setDateUpdate(localDateTime);
        return weightMeasurementsDao.save(weightMeasurements);
    }

    @Override
    public List<WeightMeasurements> getAll() {
        return weightMeasurementsDao.findAll();
    }

    @Override
    public WeightMeasurements update(WeightMeasurements weightMeasurements, long id, long idProfile) {
        WeightMeasurements updateWeightMeasurement = getById(id);
        Profile profile = profileService.getById(idProfile);
        updateWeightMeasurement.setProfile(profile);
        updateWeightMeasurement.setWeight(weightMeasurements.getWeight());
        updateWeightMeasurement.setDateUpdate(LocalDateTime.now());
        return save(updateWeightMeasurement, idProfile);
    }

    @Override
    public void delete(long id) {
        weightMeasurementsDao.deleteById(id);
    }
}
