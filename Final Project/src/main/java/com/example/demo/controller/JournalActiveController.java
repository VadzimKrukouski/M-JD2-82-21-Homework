package com.example.demo.controller;

import com.example.demo.model.JournalActive;
import com.example.demo.service.api.IJournalActiveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("/api/profile/{id_profile}/journal/active")
public class JournalActiveController {
    private final IJournalActiveService journalActive;

    public JournalActiveController(IJournalActiveService journalActive) {
        this.journalActive = journalActive;
    }

    @GetMapping
    public ResponseEntity<List<JournalActive>> getListWorkouts(@PathVariable(name = "id_profile") long idProfile,
                                                               @RequestParam(value = "page", defaultValue = "0") int page,
                                                               @RequestParam(value = "size", defaultValue = "10") int size,
                                                               @RequestParam(value = "dt_start", required = false) long dateStart,
                                                               @RequestParam(value = "dt_end", required = false) long dateEnd) {
        try {
            LocalDateTime localDateStart = LocalDateTime.ofInstant(Instant.ofEpochMilli(dateStart), ZoneId.systemDefault());
            LocalDateTime localDateEnd = LocalDateTime.ofInstant(Instant.ofEpochMilli(dateEnd), ZoneId.systemDefault());
            Pageable pageRequest = PageRequest.of(page, size);
            Page<JournalActive> journalActivePage = journalActive.findAllByProfileIdAndDateCreateBetween(idProfile, localDateStart, localDateEnd, pageRequest);
            List<JournalActive> journalActiveList = journalActivePage.getContent();
            return new ResponseEntity<>(journalActiveList, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{id_active}")
    public ResponseEntity<JournalActive> getWorkoutById(@PathVariable(name = "id_profile") long idProfile,
                                                        @PathVariable(name = "id_active") long idWorkout) {
        try {
            JournalActive journalActive = this.journalActive.findAllByProfileIdAndId(idProfile, idWorkout);
            return new ResponseEntity<>(journalActive, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<JournalActive> addWorkout(@PathVariable(name = "id_profile") long idProfile,
                                                    @RequestBody @Valid JournalActive journalActive) {
        try {
            JournalActive newJournalActive = this.journalActive.save(journalActive, idProfile);
            return new ResponseEntity<>(newJournalActive, HttpStatus.CREATED);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

    @PutMapping("/{id_active}/dt_update/{dt_update}")
    public ResponseEntity<JournalActive> updateWorkout(@PathVariable(name = "id_profile") long idProfile,
                                                       @RequestBody @Valid JournalActive journalActive,
                                                       @PathVariable(name = "id_active") long idWorkout,
                                                       @PathVariable(name = "dt_update") long dateUpdate) {
        try {
            LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(dateUpdate), ZoneId.systemDefault());
            JournalActive updateJournalActive = this.journalActive.update(journalActive, idWorkout, idProfile, date);
            return new ResponseEntity<>(updateJournalActive, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

    @DeleteMapping("/{id_active}/dt_update/{dt_update}")
    public ResponseEntity<HttpStatus> deleteWorkout(@PathVariable(name = "id_profile") long idProfile,
                                                    @PathVariable(name = "id_active") long idWorkout,
                                                    @PathVariable(name = "dt_update") long dateUpdate) {
        try {
            LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(dateUpdate), ZoneId.systemDefault());
            journalActive.delete(idWorkout, date, idProfile);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
