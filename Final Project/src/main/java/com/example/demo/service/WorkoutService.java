package com.example.demo.service;

import com.example.demo.dao.api.IWorkoutDao;
import com.example.demo.model.Workout;
import com.example.demo.service.api.IWorkoutService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService implements IWorkoutService {
    private final IWorkoutDao workout;

    public WorkoutService(IWorkoutDao workout) {
        this.workout = workout;
    }

    @Override
    public Workout getById(long id) {
        return workout.findById(id).orElse(null);
    }

    @Override
    public Workout save(Workout model) {
        return workout.save(model);
    }

    @Override
    public List<Workout> getAll() {
        return workout.findAll();
    }

    @Override
    public Workout update(Workout model, long id) {
        Workout updateWorkout = getById(id);
        updateWorkout.setName(model.getName());
        updateWorkout.setProfile(model.getProfile());
        updateWorkout.setCalories(model.getCalories());
        return save(updateWorkout);
    }

    @Override
    public void delete(long id) {
        workout.deleteById(id);

    }
}
