package com.example.demo.controller;

import com.example.demo.model.JournalWeight;
import com.example.demo.service.api.IJournalWeightService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("/api/profile/{id_profile}/journal/weight")
public class JournalWeightController {
    private final IJournalWeightService journalWeightService;

    public JournalWeightController(IJournalWeightService journalWeightService) {
        this.journalWeightService = journalWeightService;
    }

    @GetMapping
    public ResponseEntity<List<JournalWeight>> getListWeightMeasurements(@PathVariable(name = "id_profile") long idProfile,
                                                                         @RequestParam(value = "page", defaultValue = "0") int page,
                                                                         @RequestParam(value = "size", defaultValue = "10") int size,
                                                                         @RequestParam(value = "dt_start", required = false) long dateStart,
                                                                         @RequestParam(value = "dt_end", required = false) long dateEnd) {
        try {
            LocalDateTime localDateStart = LocalDateTime.ofInstant(Instant.ofEpochMilli(dateStart), ZoneId.systemDefault());
            LocalDateTime localDateEnd = LocalDateTime.ofInstant(Instant.ofEpochMilli(dateEnd), ZoneId.systemDefault());
            Pageable pageRequest = PageRequest.of(page, size);
            Page<JournalWeight> journalWeightPage = journalWeightService.findAllByProfileIdAndDateCreateBetween(idProfile, localDateStart, localDateEnd, pageRequest);
            List<JournalWeight> journalWeightList = journalWeightPage.getContent();
            return new ResponseEntity<>(journalWeightList, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id_weight}")
    public ResponseEntity<JournalWeight> getWeightMeasurementsById(@PathVariable(name = "id_profile") long idProfile,
                                                                   @PathVariable(name = "id_weight") long idJournalWeight) {
        try {
            JournalWeight journalWeight = journalWeightService.getByIdProfileAndId(idProfile, idJournalWeight);
            return new ResponseEntity<>(journalWeight, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<JournalWeight> addWeightMeasurement(@PathVariable(name = "id_profile") long idProfile,
                                                              @RequestBody JournalWeight journalWeight) {

        try {
            JournalWeight newMeasurements = journalWeightService.save(journalWeight, idProfile);
            return new ResponseEntity<>(newMeasurements, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id_weight}/dt_update/{dt_update}")
    public ResponseEntity<JournalWeight> updateWeightMeasurement(@PathVariable(name = "id_profile") long idProfile,
                                                                 @RequestBody JournalWeight journalWeight,
                                                                 @PathVariable(name = "id_weight") long idWeight,
                                                                 @PathVariable(name = "dt_update") long dateUpdate) {
        try {
            LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(dateUpdate), ZoneId.systemDefault());
            JournalWeight updateJournalWeight = journalWeightService.update(journalWeight, idWeight, idProfile, date);
            return new ResponseEntity<>(updateJournalWeight, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id_weight}/dt_update/{dt_update}")
    public ResponseEntity<HttpStatus> deleteWeightMeasurement(@PathVariable(name = "id_profile") long idProfile,
                                                              @PathVariable(name = "id_weight") long idWeightMeasurement,
                                                              @PathVariable(name = "dt_update") long dateUpdate) {
        try {
            LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(dateUpdate), ZoneId.systemDefault());
            journalWeightService.delete(idWeightMeasurement, date);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
