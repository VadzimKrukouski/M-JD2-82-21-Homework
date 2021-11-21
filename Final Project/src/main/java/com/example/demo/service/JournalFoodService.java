package com.example.demo.service;

import com.example.demo.dao.api.IJournalFoodDao;
import com.example.demo.dto.CalculationCaloriesDTO;
import com.example.demo.model.JournalFood;
import com.example.demo.model.Product;
import com.example.demo.model.Profile;
import com.example.demo.model.Recipe;
import com.example.demo.security.CheckUserAndProfile;
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
    private final CheckUserAndProfile checkProfileId;


    public JournalFoodService(IJournalFoodDao journalDao,
                              IProfileService profileService,
                              ICalculationCaloriesService calculationCaloriesService,
                              IProductService productService,
                              IRecipeService recipeService,
                              CheckUserAndProfile checkProfileId) {
        this.journalDao = journalDao;
        this.profileService = profileService;
        this.calculationCaloriesService = calculationCaloriesService;
        this.productService = productService;
        this.recipeService = recipeService;
        this.checkProfileId = checkProfileId;
    }

    @Override
    public JournalFood getById(long id) {
        return journalDao.findById(id).orElse(null);
    }

    @Override
    public CalculationCaloriesDTO getByIdAndProfileId(long idProfile, long idFood) {
        if (checkProfileId.checkProfileId(idProfile)){
            List<JournalFood> journalFoodList = new ArrayList<>();
            JournalFood journal = journalDao.findJournalByProfileIdAndId(idProfile, idFood);
            if (journal == null) {
                throw new IllegalArgumentException("Journal not found");
            }
            journalFoodList.add(journal);
            return calculationCaloriesService.calculation(journalFoodList);
        } else {
            throw new IllegalArgumentException("No access rights to this profile");
        }
    }

    @Override
    public JournalFood save(JournalFood journalFood, long idProfile) {
        if (checkProfileId.checkProfileId(idProfile)){
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
            LocalDateTime localDateTime = LocalDateTime.now().withNano(0);
            journalFood.setDateCreate(localDateTime);
            journalFood.setDateUpdate(localDateTime);
            return journalDao.save(journalFood);
        } else {
            throw new IllegalArgumentException("No access rights to this profile");
        }

    }

    @Override
    public Page<JournalFood> getAll(long idProfile, Pageable pageable) {
        if (checkProfileId.checkProfileId(idProfile)) {
            return journalDao.findAllByProfileId(idProfile, pageable);
        } else {
            throw new IllegalArgumentException("No access rights to this profile");
        }
    }

    @Override
    public JournalFood update(JournalFood journalFood, long idFood, long idProfile, LocalDateTime dateUpdate) {
        if (checkProfileId.checkProfileId(idProfile)){
            JournalFood updateJournalFood = getById(idFood);

            if (updateJournalFood == null) {
                throw new IllegalArgumentException("JournalFood is not found by ID");
            }

            if (journalFood.getProduct() != null) {
                Product product = productService.getById(journalFood.getProduct().getId());
                updateJournalFood.setProduct(product);
            }
            if (journalFood.getRecipe() != null) {
                Recipe recipe = recipeService.getById(journalFood.getRecipe().getId());
                updateJournalFood.setRecipe(recipe);
            }

            updateJournalFood.setWeight(journalFood.getWeight());
            updateJournalFood.setMealTime(journalFood.getMealTime());

            if (updateJournalFood.getDateUpdate().isEqual(dateUpdate)) {
                return journalDao.save(updateJournalFood);
            } else {
                throw new IllegalArgumentException("Optimistic lock. JournalFood already updated");
            }
        } else {
            throw new IllegalArgumentException("No access rights to this profile");
        }
    }

    @Override
    public void delete(long id, LocalDateTime dateUpdate, long idProfile) {
        if (checkProfileId.checkProfileId(idProfile)){
            JournalFood journalFood = getById(id);
            if (journalFood == null) {
                throw new IllegalArgumentException("JournalFood is not found by ID");
            }
            if (journalFood.getDateUpdate().isEqual(dateUpdate)){
                journalDao.deleteById(id);
            } else {
                throw new IllegalArgumentException("Optimistic lock. JournalFood already updated");
            }
        } else {
            throw new IllegalArgumentException("No access rights to this profile");
        }
    }

    @Override
    public CalculationCaloriesDTO findAllByProfileIdAndDateCreateBetween(long idProfile, LocalDateTime dateStart, LocalDateTime dateEnd) {
        if (checkProfileId.checkProfileId(idProfile)){
            List<JournalFood> journalFoodList = journalDao.findAllByProfileIdAndDateCreateBetween(idProfile, dateStart, dateEnd);
            return calculationCaloriesService.calculation(journalFoodList);
        } else {
            throw new IllegalArgumentException("No access rights to this profile");
        }
    }

}
