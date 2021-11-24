package com.example.demo.service.api;

import com.example.demo.model.JournalActive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface IJournalActiveService {
    JournalActive getById(long id);
    JournalActive findAllByProfileIdAndId(long idProfile, long idWorkout);
    Page<JournalActive> findAllByProfileIdAndDateCreateBetween (long idProfile, LocalDateTime dateStart, LocalDateTime dateEnd, Pageable pageable);
    JournalActive save(JournalActive journalActive, long idProfile);
    List<JournalActive> getAll();
    JournalActive update(JournalActive journalActive, long idWorkout, long idProfile, LocalDateTime date);
    void delete(long id, LocalDateTime date, long idProfile);
}
