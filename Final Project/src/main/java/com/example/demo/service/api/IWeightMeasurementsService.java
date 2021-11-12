package com.example.demo.service.api;

import com.example.demo.model.WeightMeasurements;

import java.util.List;

public interface IWeightMeasurementsService {
    WeightMeasurements getById(long id);
    WeightMeasurements getByIdProfileAndId(long idProfile, long idWeight);
    WeightMeasurements save(WeightMeasurements weightMeasurements, long idProfile);
    List<WeightMeasurements> getAll();
    WeightMeasurements update(WeightMeasurements weightMeasurements, long id, long idProfile);
    void delete(long id);
}
