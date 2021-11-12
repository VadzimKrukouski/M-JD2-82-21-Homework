package com.example.demo.service.api;

import com.example.demo.model.Journal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface IJournalService {
    Journal getByIdAndProfileId(long idProfile, long idFood);
    Journal getById(long id);
    Journal save(Journal journal, long idProfile);
    Page<Journal> getAll(long idProfile, Pageable pageable);
    Journal update(Journal journal, long idFood, long idProfile, LocalDateTime dateUpdate);
    void delete(long id);
}
