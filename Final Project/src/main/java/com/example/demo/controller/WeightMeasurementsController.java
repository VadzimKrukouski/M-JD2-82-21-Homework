package com.example.demo.controller;

import com.example.demo.model.WeightMeasurements;
import com.example.demo.service.api.IWeightMeasurementsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/profile/{id_profile}/journal/weight")
public class WeightMeasurementsController {
    private final IWeightMeasurementsService weightMeasurementsService;

    public WeightMeasurementsController(IWeightMeasurementsService weightMeasurementsService) {
        this.weightMeasurementsService = weightMeasurementsService;
    }

    @GetMapping
    public ResponseEntity<List<WeightMeasurements>> getListWeightMeasurements(@PathVariable(name = "id_profile") long idProfile,
                                                                              @RequestParam(value = "page", defaultValue = "0") int page,
                                                                              @RequestParam(value = "size", defaultValue = "10") int size,
                                                                              @RequestParam(value = "dt_start", required = false) LocalDateTime dateStart,
                                                                              @RequestParam(value = "dt_end", required = false) LocalDateTime dateEnd) {
        try {



            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id_weight}")
    public ResponseEntity<WeightMeasurements> getWeightMeasurementsById(@PathVariable(name = "id_profile") long idProfile,
                                                                        @PathVariable(name = "id_weight") long idWeightMeasurements) {
        try {
            WeightMeasurements weightMeasurements = weightMeasurementsService.getByIdProfileAndId(idProfile, idWeightMeasurements);
            return new ResponseEntity<>(weightMeasurements, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<WeightMeasurements> addWeightMeasurement(@PathVariable(name = "id_profile") long idProfile,
                                                                   @RequestBody WeightMeasurements weightMeasurements) {

        try {
            WeightMeasurements newMeasurements = weightMeasurementsService.save(weightMeasurements, idProfile);
            return new ResponseEntity<>(newMeasurements, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id_weight}/dt_update/{dt_update}")
    public ResponseEntity<WeightMeasurements> updateWeightMeasurement(@PathVariable(name = "id_profile") long idProfile,
                                                                      @RequestBody WeightMeasurements weightMeasurements,
                                                                      @PathVariable(name = "id_weight") long idWeight,
                                                                      @PathVariable(name = "dt_update") LocalDateTime dateUpdate) {
//        todo
        try {


            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id_weight}/dt_update/{dt_update}")
    public ResponseEntity<HttpStatus> deleteWeightMeasurement(@PathVariable(name = "id_profile") long idProfile,
                                                              @PathVariable(name = "id_weight") long idWeightMeasurement,
                                                              @PathVariable(name = "dt_update") LocalDateTime dateUpdate) {
//        todo
        try {


            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
