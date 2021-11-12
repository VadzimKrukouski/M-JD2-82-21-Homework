package com.example.demo.controller;

import com.example.demo.model.Journal;
import com.example.demo.service.JournalService;
import com.example.demo.service.api.IJournalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/profile")
public class JournalController {
    private final IJournalService journalService;

    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    @GetMapping("/{id_profile}/journal/food")
    public ResponseEntity<List<Journal>> getListJournals(@PathVariable(name = "id_profile") long idProfile,
                                                         @RequestParam(value = "page", defaultValue = "0") int page,
                                                         @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<Journal> journalPage = journalService.getAll(idProfile, pageRequest);
        List<Journal> journalList = journalPage.getContent();
        return new ResponseEntity<>(journalList, HttpStatus.OK);
    }

    @GetMapping("/{id_profile}/journal/food/{day}")
    public ResponseEntity<List<Journal>> getListJournalDay(@PathVariable(name = "id_profile") long idProfile,
                                                           @PathVariable(name = "day") int day) {
//        todo
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id_profile}/journal/food/{id_food}")
    public ResponseEntity<Journal> getJournal(@PathVariable(name = "id_profile") long idProfile,
                                              @PathVariable(name = "id_food") long idFood) {
        Journal journal = journalService.getByIdAndProfileId(idProfile, idFood);
        return new ResponseEntity<>(journal, HttpStatus.OK);
    }

    @PostMapping("/{id_profile}/journal/food")
    public ResponseEntity<Journal> addJournal(@PathVariable(name = "id_profile") long idProfile,
                                              @RequestBody Journal journal) {
        Journal newJournal = journalService.save(journal, idProfile);
        return new ResponseEntity<>(newJournal, HttpStatus.CREATED);
    }

    @PutMapping("/{id_profile}/journal/food/{id_food}/dt_update/{dt_update}")
    public ResponseEntity<Journal> updateJournal(@PathVariable(name = "id_profile") long idProfile,
                                                 @PathVariable(name = "id_food") long idFood,
                                                 @PathVariable(name = "dt_update") LocalDateTime dateUpdate) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id_profile}/journal/food/{id_food}/dt_update/{dt_update}")
    public ResponseEntity<HttpStatus> deleteJournal(@PathVariable(name = "id_profile") long idProfile,
                                                    @PathVariable(name = "id_food") long idFood,
                                                    @PathVariable(name = "dt_update") LocalDateTime dateUpdate) {
//        todo
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
