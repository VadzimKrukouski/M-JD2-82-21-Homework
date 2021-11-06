package com.example.demo.service;

import com.example.demo.dao.api.IWeightMeasurementsDao;
import com.example.demo.model.WeightMeasurements;
import com.example.demo.service.api.IWeightMeasurementsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeightMeasurementsService implements IWeightMeasurementsService {
    private final IWeightMeasurementsDao weightMeasurements;

    public WeightMeasurementsService(IWeightMeasurementsDao weightMeasurements) {
        this.weightMeasurements = weightMeasurements;
    }

    @Override
    public WeightMeasurements getById(long id) {
        return weightMeasurements.findById(id).orElse(null);
    }

    @Override
    public WeightMeasurements save(WeightMeasurements model) {
        return weightMeasurements.save(model);
    }

    @Override
    public List<WeightMeasurements> getAll() {
        return weightMeasurements.findAll();
    }

    @Override
    public WeightMeasurements update(WeightMeasurements model, long id) {
        WeightMeasurements updateWeightMeasurement = getById(id);
        updateWeightMeasurement.setProfile(model.getProfile());
        updateWeightMeasurement.setValue(model.getValue());
        return save(updateWeightMeasurement);
    }

    @Override
    public void delete(long id) {
        weightMeasurements.deleteById(id);
    }
}
