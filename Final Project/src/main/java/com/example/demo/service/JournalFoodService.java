package com.example.demo.service;

import com.example.demo.dao.api.IJournalFoodDao;
import com.example.demo.dto.CalculationCaloriesDTO;
import com.example.demo.model.JournalFood;
import com.example.demo.model.Product;
import com.example.demo.model.Profile;
import com.example.demo.service.api.ICalculationCaloriesService;
import com.example.demo.service.api.IJournalFoodService;
import com.example.demo.service.api.IProductService;
import com.example.demo.service.api.IProfileService;
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

    public JournalFoodService(IJournalFoodDao journalDao, IProfileService profileService, ICalculationCaloriesService calculationCaloriesService, IProductService productService) {
        this.journalDao = journalDao;
        this.profileService = profileService;
        this.calculationCaloriesService = calculationCaloriesService;
        this.productService = productService;
    }

    @Override
    public JournalFood getById(long id) {
        return journalDao.findById(id).orElse(null);
    }

    @Override
    public CalculationCaloriesDTO getByIdAndProfileId(long idProfile, long idFood) {
        List<JournalFood> journalFoodList = new ArrayList<>();
        JournalFood journal = journalDao.findJournalByProfileIdAndId(idProfile, idFood);
        journalFoodList.add(journal);
        return calculationCaloriesService.calculation(journalFoodList);
    }

    @Override
    public JournalFood save(JournalFood journalFood, long idProfile) {
        Profile profile = profileService.getById(idProfile);
        journalFood.setProfile(profile);
        Product product = productService.getById(journalFood.getProduct().getId());
        journalFood.setProduct(product);
        LocalDateTime localDateTime = LocalDateTime.now();
        journalFood.setDateCreate(localDateTime);
        journalFood.setDateUpdate(localDateTime);
        return journalDao.save(journalFood);
    }

    @Override
    public Page<JournalFood> getAll(long idProfile, Pageable pageable) {
        return journalDao.findAllByProfileId(idProfile,pageable);
    }

    @Override
    public JournalFood update(JournalFood journalFood, long idFood, long idProfile, LocalDateTime dateUpdate) {
        JournalFood updateJournalFood = getById(idFood);
        updateJournalFood.setRecipe(journalFood.getRecipe());
        updateJournalFood.setProduct(journalFood.getProduct());
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
