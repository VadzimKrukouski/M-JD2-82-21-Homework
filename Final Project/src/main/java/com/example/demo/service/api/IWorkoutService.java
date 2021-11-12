package com.example.demo.service.api;

import com.example.demo.model.Workout;

import java.util.List;

public interface IWorkoutService {
    Workout getById(long id);
    Workout findAllByProfileIdAndId(long idProfile, long idWorkout);
    Workout save(Workout workout, long idProfile);
    List<Workout> getAll();
    Workout update(Workout workout, long idWorkout, long idProfile);
    void delete(long id);
}
