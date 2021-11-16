package com.example.demo.dao.api;

import com.example.demo.model.JournalFood;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IJournalFoodDao extends JpaRepository<JournalFood, Long> {
    Page<JournalFood> findAllByProfileId(long idProfile, Pageable pageable);

    JournalFood findJournalByProfileIdAndId(long idProfile, long idFood);
}
