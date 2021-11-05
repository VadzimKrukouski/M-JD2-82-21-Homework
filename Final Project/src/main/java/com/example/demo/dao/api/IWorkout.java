package com.example.demo.dao.api;

import com.example.demo.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWorkout extends JpaRepository<Workout, Long> {
}
