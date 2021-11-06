package com.example.demo.controller;

import com.example.demo.model.WeightMeasurements;
import com.example.demo.model.Workout;
import com.example.demo.service.api.IWorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/profile/{id_profile}/journal/active")
public class WorkoutController {
    private final IWorkoutService workoutService;

    public WorkoutController(IWorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping
    public ResponseEntity<List<Workout>> getListWorkouts (@PathVariable(name = "id_profile") long idProfile,
                                                          @RequestParam(value = "page", defaultValue = "0") int page,
                                                          @RequestParam(value = "size", defaultValue = "10") int size,
                                                          @RequestParam(value = "dt_start", required = false) LocalDateTime dateStart,
                                                          @RequestParam(value = "dt_end", required = false)LocalDateTime dateEnd){
//        todo
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id_active}")
    public ResponseEntity<Workout> getWorkoutById(@PathVariable(name = "id_profile") long idProfile,
                                                  @PathVariable(name = "id_active") long idWorkout){
//        todo
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Workout> addWorkout(@PathVariable(name = "id_profile") long idProfile,
                                              @RequestBody Workout workout){
//        todo
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id_active}/dt_update/{dt_update}")
    public ResponseEntity<Workout> updateWorkout(@PathVariable(name = "id_profile") long idProfile,
                                                 @RequestBody Workout workout,
                                                 @PathVariable (name = "id_active") long idActive,
                                                 @PathVariable (name = "dt_update") LocalDateTime dateUpdate){
//        todo
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id_active}/dt_update/{dt_update}")
    public ResponseEntity<HttpStatus> deleteWorkout (@PathVariable(name = "id_profile") long idProfile,
                                                     @PathVariable(name = "id_active") long idWorkout,
                                                     @PathVariable (name = "dt_update") LocalDateTime dateUpdate){
//        todo
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
