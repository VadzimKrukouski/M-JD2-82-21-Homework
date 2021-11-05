package com.example.demo.service;

import com.example.demo.dao.api.IWeightMeasurements;
import com.example.demo.model.WeightMeasurements;
import com.example.demo.service.api.IAppService;

import java.util.List;

public class WeightMeasurementsService implements IAppService<WeightMeasurements> {
    private final IWeightMeasurements weightMeasurements;

    public WeightMeasurementsService(IWeightMeasurements weightMeasurements) {
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
