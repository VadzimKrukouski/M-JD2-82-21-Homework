package com.example.demo.dao.api;

import com.example.demo.model.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IJournalDao extends JpaRepository<Journal, Long> {
}