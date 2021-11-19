package com.example.demo.dao.api;

import com.example.demo.model.JournalActive;
import com.example.demo.model.JournalWeight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface IJournalActiveDao extends JpaRepository<JournalActive, Long> {
    JournalActive findAllByProfileIdAndId(long idProfile, long idWorkout);
    Page<JournalActive> findAllByProfileIdAndDateCreateBetween(long idProfile, LocalDateTime dateStart, LocalDateTime dateEnd, Pageable pageable);
}
