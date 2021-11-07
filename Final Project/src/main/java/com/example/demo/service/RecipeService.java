package com.example.demo.service;

import com.example.demo.dao.api.IRecipeDao;
import com.example.demo.model.Recipe;
import com.example.demo.service.api.IRecipeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService implements IRecipeService {
    private final IRecipeDao dishDao;

    public RecipeService(IRecipeDao dishDao) {
        this.dishDao = dishDao;
    }

    @Override
    public Recipe getById(long id) {
        return dishDao.findById(id).orElse(null);
    }

    @Override
    public Recipe save(Recipe model) {
        return dishDao.save(model);
    }

    @Override
    public List<Recipe> getAll() {
        return dishDao.findAll();
    }

    @Override
    public Recipe update(Recipe model, long id) {
        Recipe updateRecipe = getById(id);
        updateRecipe.setName(model.getName());
        updateRecipe.setProducts(model.getProducts());
        updateRecipe.setUserName(model.getUserName());
        return save(updateRecipe);
    }

    @Override
    public void delete(long id) {
        dishDao.deleteById(id);
    }
}
