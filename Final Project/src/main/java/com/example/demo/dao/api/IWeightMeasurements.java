package com.example.demo.dao.api;

import com.example.demo.model.WeightMeasurements;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWeightMeasurements extends JpaRepository<WeightMeasurements, Long> {
}
