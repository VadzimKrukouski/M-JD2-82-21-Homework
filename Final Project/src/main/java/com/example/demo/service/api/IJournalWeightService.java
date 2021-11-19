package com.example.demo.service.api;

import com.example.demo.model.JournalWeight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface IJournalWeightService {
    JournalWeight getById(long id);
    JournalWeight getByIdProfileAndId(long idProfile, long idWeight);
    Page<JournalWeight>  findAllByProfileIdAndDateCreateBetween (long idProfile, LocalDateTime dateStart, LocalDateTime dateEnd, Pageable pageable);
    JournalWeight save(JournalWeight journalWeight, long idProfile);
    List<JournalWeight> getAll();
    JournalWeight update(JournalWeight journalWeight, long id, long idProfile, LocalDateTime date);
    void delete(long id, LocalDateTime date);
}
