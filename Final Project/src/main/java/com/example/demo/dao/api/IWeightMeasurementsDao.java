package com.example.demo.dao.api;

import com.example.demo.model.WeightMeasurements;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWeightMeasurementsDao extends JpaRepository<WeightMeasurements, Long> {
}
