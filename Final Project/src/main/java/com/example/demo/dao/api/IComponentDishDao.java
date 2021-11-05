package com.example.demo.dao.api;

import com.example.demo.model.ComponentDish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IComponentDishDao extends JpaRepository<ComponentDish, Long> {
}
