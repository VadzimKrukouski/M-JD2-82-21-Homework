package com.example.demo.service;

import com.example.demo.dao.api.IComponentDishDao;
import com.example.demo.dao.api.IRecipeDao;
import com.example.demo.model.ComponentDish;
import com.example.demo.model.Recipe;
import com.example.demo.service.api.IRecipeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecipeService implements IRecipeService {
    private final IRecipeDao dishDao;
    private final IComponentDishDao componentDishDao;

    public RecipeService(IRecipeDao dishDao, IComponentDishDao componentDishDao) {
        this.dishDao = dishDao;
        this.componentDishDao = componentDishDao;
    }

    @Override
    public Recipe getById(long id) {
        return dishDao.findById(id).orElse(null);
    }

    @Override
    public Recipe save(Recipe recipe) {
        LocalDateTime localDateTime = LocalDateTime.now();
        recipe.setDateCreate(localDateTime);
        recipe.setDateUpdate(localDateTime);
        List<ComponentDish> componentDishes = recipe.getComponentDishes();
        for (ComponentDish componentDish : componentDishes) {
            componentDish.setDateCreate(recipe.getDateCreate());
            componentDish.setDateUpdate(recipe.getDateUpdate());
            componentDishDao.save(componentDish);
        }
        return dishDao.save(recipe);
    }

    @Override
    public Page<Recipe> getAll(Pageable pageable) {
        return dishDao.findAll(pageable);
    }

    @Override
    public Recipe update(Recipe recipe, long id) {
        Recipe updateRecipe = getById(id);
        updateRecipe.setName(recipe.getName());
        updateRecipe.setComponentDishes(recipe.getComponentDishes());
        updateRecipe.setUser(recipe.getUser());
        updateRecipe.setDateUpdate(LocalDateTime.now());
        return save(updateRecipe);
    }

    @Override
    public void delete(long id) {
        dishDao.deleteById(id);
    }
}
