package com.example.demo.service.api;

import com.example.demo.model.Recipe;

import java.util.List;

public interface IRecipeService {
    Recipe getById(long id);
    Recipe save(Recipe model);
    List<Recipe> getAll();
    Recipe update(Recipe model, long id);
    void delete(long id);
}

