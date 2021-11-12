package com.example.demo.dao.api;

import com.example.demo.model.Journal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IJournalDao extends JpaRepository<Journal, Long> {
    Page<Journal> findAllByProfileId(long idProfile, Pageable pageable);

    Journal findJournalByProfileIdAndId(long idProfile, long idFood);
}
