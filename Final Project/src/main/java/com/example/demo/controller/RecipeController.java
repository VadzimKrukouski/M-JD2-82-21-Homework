package com.example.demo.controller;

import com.example.demo.model.Recipe;
import com.example.demo.service.RecipeService;
import com.example.demo.service.api.IRecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {
    private final IRecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public ResponseEntity<List<Recipe>> getRecipe(@RequestParam(value = "page", defaultValue = "0") int page,
                                                  @RequestParam(value = "size", defaultValue = "10") int size,
                                                  @RequestParam(required = false) String name) {
        List<Recipe> recipeList = recipeService.getAll();
        return new ResponseEntity<>(recipeList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable(name = "id") long id) {
        Recipe recipe = recipeService.getById(id);
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        Recipe newRecipe = recipeService.save(recipe);
        return new ResponseEntity<>(newRecipe, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/dt_update/{dt_update}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable(name = "id") long id,
                                               @RequestBody Recipe recipe,
                                               @PathVariable (name = "dt_update") LocalDateTime dateUpdate) {
        Recipe updateRecipe = recipeService.update(recipe, id);
        return new ResponseEntity<>(updateRecipe, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/dt_update/{dt_update}")
    public ResponseEntity<HttpStatus> deleteRecipe(@PathVariable(name = "id") long id,
                                               @PathVariable(name = "dt_update") LocalDateTime dateUpdate) {
        recipeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
