package com.example.demo.service;

import com.example.demo.dao.api.IComponentDishDao;
import com.example.demo.dao.api.IRecipeDao;
import com.example.demo.model.ComponentDish;
import com.example.demo.model.Recipe;
import com.example.demo.model.User;
import com.example.demo.security.UserHolder;
import com.example.demo.service.api.IComponentDishService;
import com.example.demo.service.api.IRecipeService;
import com.example.demo.service.api.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecipeService implements IRecipeService {
    private final IRecipeDao dishDao;
    private final IComponentDishService componentDishService;
    private final IUserService userService;
    private final UserHolder userHolder;

    public RecipeService(IRecipeDao dishDao, IComponentDishDao componentDishDao, IComponentDishService componentDishService, IUserService userService, UserHolder userHolder) {
        this.dishDao = dishDao;
        this.componentDishService = componentDishService;
        this.userService = userService;
        this.userHolder = userHolder;
    }

    @Override
    public Recipe getById(long id) {
        return dishDao.findById(id).orElse(null);
    }

    @Override
    public Recipe save(Recipe recipe) {
        String loginUser = userHolder.getAuthentication().getName();
        User user = userService.findUserByLogin(loginUser);
        recipe.setUser(user);
        List<ComponentDish> componentDishes = recipe.getComponentDishes();
        for (ComponentDish componentDish : componentDishes) {
            componentDish.setDateCreate(recipe.getDateCreate());
            componentDish.setDateUpdate(recipe.getDateUpdate());
            componentDishService.save(componentDish);
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        recipe.setDateCreate(localDateTime);
        recipe.setDateUpdate(localDateTime);
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
        List<ComponentDish> componentDishes = recipe.getComponentDishes();
        for (ComponentDish componentDish : componentDishes) {
            componentDish.setDateCreate(recipe.getDateCreate());
            componentDish.setDateUpdate(recipe.getDateUpdate());
            componentDishService.save(componentDish);
        }
        updateRecipe.setUser(recipe.getUser());
        updateRecipe.setDateUpdate(LocalDateTime.now());
        return dishDao.save(updateRecipe);
    }

    @Override
    public void delete(long id) {
        dishDao.deleteById(id);
    }
}
