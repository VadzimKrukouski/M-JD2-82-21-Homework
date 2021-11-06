package com.example.demo.dao.api;

import com.example.demo.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDishDao extends JpaRepository<Recipe, Long> {
}
