package com.example.demo.dao.api;

import com.example.demo.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWorkoutDao extends JpaRepository<Workout, Long> {
}
