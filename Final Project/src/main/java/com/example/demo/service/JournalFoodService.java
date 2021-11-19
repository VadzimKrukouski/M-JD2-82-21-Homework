package com.example.demo.service;

import com.example.demo.dao.api.IJournalFoodDao;
import com.example.demo.dto.CalculationCaloriesDTO;
import com.example.demo.model.JournalFood;
import com.example.demo.model.Product;
import com.example.demo.model.Profile;
import com.example.demo.model.Recipe;
import com.example.demo.service.api.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class JournalFoodService implements IJournalFoodService {
    private final IJournalFoodDao journalDao;
    private final IProfileService profileService;
    private final ICalculationCaloriesService calculationCaloriesService;
    private final IProductService productService;
    private final IRecipeService recipeService;

    public JournalFoodService(IJournalFoodDao journalDao,
                              IProfileService profileService,
                              ICalculationCaloriesService calculationCaloriesService,
                              IProductService productService,
                              IRecipeService recipeService) {
        this.journalDao = journalDao;
        this.profileService = profileService;
        this.calculationCaloriesService = calculationCaloriesService;
        this.productService = productService;
        this.recipeService = recipeService;
    }

    @Override
    public JournalFood getById(long id) {
        return journalDao.findById(id).orElse(null);
    }

    @Override
    public CalculationCaloriesDTO getByIdAndProfileId(long idProfile, long idFood) {
        List<JournalFood> journalFoodList = new ArrayList<>();
        JournalFood journal = journalDao.findJournalByProfileIdAndId(idProfile, idFood);
        if (journal == null) {
            throw new IllegalArgumentException("Journal not found");
        }
        journalFoodList.add(journal);
        return calculationCaloriesService.calculation(journalFoodList);
    }

    @Override
    public JournalFood save(JournalFood journalFood, long idProfile) {
        Profile profile = profileService.getById(idProfile);
        journalFood.setProfile(profile);
        if (journalFood.getProduct() != null) {
            Product product = productService.getById(journalFood.getProduct().getId());
            journalFood.setProduct(product);
        }
        if (journalFood.getRecipe() != null) {
            Recipe recipe = recipeService.getById(journalFood.getRecipe().getId());
            journalFood.setRecipe(recipe);
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        journalFood.setDateCreate(localDateTime);
        journalFood.setDateUpdate(localDateTime);
        return journalDao.save(journalFood);
    }

    @Override
    public Page<JournalFood> getAll(long idProfile, Pageable pageable) {
        return journalDao.findAllByProfileId(idProfile, pageable);
    }

    @Override
    public JournalFood update(JournalFood journalFood, long idFood, long idProfile, LocalDateTime dateUpdate) {
        JournalFood updateJournalFood = getById(idFood);

        if (updateJournalFood == null) {
            throw new IllegalArgumentException("JournalFood is not found by ID");
        }

        if (journalFood.getProduct() != null) {
            Product product = productService.getById(journalFood.getProduct().getId());
            journalFood.setProduct(product);
        }
        if (journalFood.getRecipe() != null) {
            Recipe recipe = recipeService.getById(journalFood.getRecipe().getId());
            journalFood.setRecipe(recipe);
        }

        updateJournalFood.setWeight(journalFood.getWeight());
        updateJournalFood.setMealTime(journalFood.getMealTime());
        updateJournalFood.setDateUpdate(LocalDateTime.now());

        return journalDao.save(updateJournalFood);
    }

    @Override
    public void delete(long id) {
        journalDao.deleteById(id);
    }

}
