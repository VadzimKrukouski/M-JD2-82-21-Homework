package com.example.demo.dao.api;

import com.example.demo.model.JournalFood;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IJournalFoodDao extends JpaRepository<JournalFood, Long> {
    Page<JournalFood> findAllByProfileId(long idProfile, Pageable pageable);

    JournalFood findJournalByProfileIdAndId(long idProfile, long idFood);

    List<JournalFood> findAllByProfileIdAndDateCreateBetween(long idProfile, LocalDateTime dateStart, LocalDateTime dateEnd);
}
