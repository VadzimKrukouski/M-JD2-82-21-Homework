package com.example.demo.dao.api;

import com.example.demo.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDishDao extends JpaRepository<Dish, Long> {
}
