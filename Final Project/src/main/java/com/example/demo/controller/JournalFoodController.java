package com.example.demo.controller;

import com.example.demo.dto.CalculationCaloriesDTO;
import com.example.demo.model.JournalFood;
import com.example.demo.service.JournalFoodService;
import com.example.demo.service.api.IJournalFoodService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/profile")
public class JournalFoodController {
    private final IJournalFoodService journalFoodService;

    public JournalFoodController(JournalFoodService journalFoodService) {
        this.journalFoodService = journalFoodService;
    }

    @GetMapping("/{id_profile}/journal/food")
    public ResponseEntity<List<JournalFood>> getListJournals(@PathVariable(name = "id_profile") long idProfile,
                                                             @RequestParam(value = "page", defaultValue = "0") int page,
                                                             @RequestParam(value = "size", defaultValue = "10") int size) {
        try {
            Pageable pageRequest = PageRequest.of(page, size);
            Page<JournalFood> journalPage = journalFoodService.getAll(idProfile, pageRequest);
            List<JournalFood> journalFoodList = journalPage.getContent();
            return new ResponseEntity<>(journalFoodList, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id_profile}/journal/food/byDay/{day}")
    public ResponseEntity<List<JournalFood>> getListJournalDay(@PathVariable(name = "id_profile") long idProfile,
                                                               @PathVariable(name = "day") int day) {
//        todo
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id_profile}/journal/food/{id_food}")
    public ResponseEntity<CalculationCaloriesDTO> getJournal(@PathVariable(name = "id_profile") long idProfile,
                                                             @PathVariable(name = "id_food") long idFood) {
        try {
            CalculationCaloriesDTO journalFood = journalFoodService.getByIdAndProfileId(idProfile, idFood);
            return new ResponseEntity<>(journalFood, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id_profile}/journal/food")
    public ResponseEntity<JournalFood> addJournal(@PathVariable(name = "id_profile") long idProfile,
                                                  @RequestBody JournalFood journalFood) {
        try {
            JournalFood newJournalFood = journalFoodService.save(journalFood, idProfile);
            return new ResponseEntity<>(newJournalFood, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id_profile}/journal/food/{id_food}/dt_update/{dt_update}")
    public ResponseEntity<JournalFood> updateJournal(@PathVariable(name = "id_profile") long idProfile,
                                                     @RequestBody JournalFood journalFood,
                                                     @PathVariable(name = "id_food") long idFood,
                                                     @PathVariable(name = "dt_update") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dateUpdate) {
        try {
            JournalFood updateJournalFood = journalFoodService.update(journalFood, idFood, idProfile, dateUpdate);
            return new ResponseEntity<>(updateJournalFood, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id_profile}/journal/food/{id_food}/dt_update/{dt_update}")
    public ResponseEntity<HttpStatus> deleteJournal(@PathVariable(name = "id_profile") long idProfile,
                                                    @PathVariable(name = "id_food") long idFood,
                                                    @PathVariable(name = "dt_update") LocalDateTime dateUpdate) {
//        todo
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
