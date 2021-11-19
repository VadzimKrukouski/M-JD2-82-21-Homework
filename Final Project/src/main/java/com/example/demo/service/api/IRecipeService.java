package com.example.demo.service.api;

import com.example.demo.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface IRecipeService {
    Recipe getById(long id);
    Recipe save(Recipe recipe);
    Page<Recipe> getAll(Pageable pageable);
    Recipe update(Recipe recipe, long id, LocalDateTime date);
    void delete(long id, LocalDateTime date);
}

