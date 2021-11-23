package com.example.demo.service.api;

import com.example.demo.dto.CalculationCaloriesDTO;
import com.example.demo.model.JournalFood;
import com.example.demo.model.Profile;

import java.util.List;

public interface ICalculationCaloriesService {
    Double getCaloriesNorm(Profile profile);
    Double getCaloriesTarget(Profile profile);
    CalculationCaloriesDTO calculation(List<JournalFood> journalFoodList);
}
