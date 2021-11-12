package com.example.demo.service;

import com.example.demo.dao.api.IWorkoutDao;
import com.example.demo.model.Profile;
import com.example.demo.model.Workout;
import com.example.demo.service.api.IProfileService;
import com.example.demo.service.api.IWorkoutService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WorkoutService implements IWorkoutService {
    private final IWorkoutDao workoutDao;
    private final IProfileService profileService;

    public WorkoutService(IWorkoutDao workoutDao, IProfileService profileService) {
        this.workoutDao = workoutDao;
        this.profileService = profileService;
    }

    @Override
    public Workout getById(long id) {
        return workoutDao.findById(id).orElse(null);
    }

    @Override
    public Workout findAllByProfileIdAndId(long idProfile, long idWorkout) {
        return workoutDao.findAllByProfileIdAndId(idProfile, idWorkout);
    }

    @Override
    public Workout save(Workout workout, long idProfile) {
        Profile profile = profileService.getById(idProfile);
        workout.setProfile(profile);
        LocalDateTime localDateTime = LocalDateTime.now();
        workout.setDateCreate(localDateTime);
        workout.setDateUpdate(localDateTime);
        return workoutDao.save(workout);
    }

    @Override
    public List<Workout> getAll() {
        return workoutDao.findAll();
    }

    @Override
    public Workout update(Workout workout, long idWorkout, long idProfile) {
        Workout updateWorkout = getById(idWorkout);
        updateWorkout.setName(workout.getName());
        updateWorkout.setProfile(workout.getProfile());
        updateWorkout.setCalories(workout.getCalories());
        return save(updateWorkout, idProfile);
    }

    @Override
    public void delete(long id) {
        workoutDao.deleteById(id);

    }
}
