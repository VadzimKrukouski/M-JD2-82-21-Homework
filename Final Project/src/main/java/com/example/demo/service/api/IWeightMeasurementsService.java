package com.example.demo.service.api;

import com.example.demo.model.WeightMeasurements;

import java.util.List;

public interface IWeightMeasurementsService {
    WeightMeasurements getById(long id);
    WeightMeasurements save(WeightMeasurements model);
    List<WeightMeasurements> getAll();
    WeightMeasurements update(WeightMeasurements model, long id);
    void delete(long id);
}
