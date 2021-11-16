package com.example.demo.service.api;

import com.example.demo.dto.CalculationCaloriesDTO;
import com.example.demo.model.JournalFood;

import java.util.List;

public interface ICalculationCaloriesService {
    CalculationCaloriesDTO calculation(List<JournalFood> journalFoodList);
}
