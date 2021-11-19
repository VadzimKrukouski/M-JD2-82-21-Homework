package com.example.demo.dao.api;

import com.example.demo.model.JournalWeight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface IJournalWeightDao extends JpaRepository<JournalWeight, Long> {
    JournalWeight findAllByProfileIdAndId (long idProfile, long idWeight);
    Page<JournalWeight> findAllByProfileIdAndDateCreateBetween(long idProfile, LocalDateTime dateStart, LocalDateTime dateEnd, Pageable pageable);
}
