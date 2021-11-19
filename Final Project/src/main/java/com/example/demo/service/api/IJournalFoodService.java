package com.example.demo.service.api;

import com.example.demo.dto.CalculationCaloriesDTO;
import com.example.demo.model.JournalFood;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface IJournalFoodService {
    CalculationCaloriesDTO getByIdAndProfileId(long idProfile, long idFood);
    JournalFood getById(long id);
    JournalFood save(JournalFood journalFood, long idProfile);
    Page<JournalFood> getAll(long idProfile, Pageable pageable);
    JournalFood update(JournalFood journalFood, long idFood, long idProfile, LocalDateTime dateUpdate);
    void delete(long id, LocalDateTime dateUpdate);
}
