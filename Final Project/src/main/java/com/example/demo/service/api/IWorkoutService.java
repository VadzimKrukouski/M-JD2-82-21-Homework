package com.example.demo.service.api;

import com.example.demo.model.Workout;

import java.util.List;

public interface IWorkoutService {
    Workout getById(long id);
    Workout save(Workout model);
    List<Workout> getAll();
    Workout update(Workout model, long id);
    void delete(long id);
}
