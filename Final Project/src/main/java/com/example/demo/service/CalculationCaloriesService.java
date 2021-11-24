package com.example.demo.service;

import com.example.demo.dto.CalculationCaloriesDTO;
import com.example.demo.model.*;
import com.example.demo.model.api.EGender;
import com.example.demo.model.api.ELifestyle;
import com.example.demo.model.api.ETarget;
import com.example.demo.service.api.ICalculationCaloriesService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class CalculationCaloriesService implements ICalculationCaloriesService {

    @Override
    public CalculationCaloriesDTO calculation(List<JournalFood> journalFoodList) {
        CalculationCaloriesDTO caloriesDTO = new CalculationCaloriesDTO();
        double calories = 0;
        double proteins = 0;
        double fats = 0;
        double carbohydrates = 0;
        double weight = 0;
        for (JournalFood journalFood : journalFoodList) {
            if (journalFood.getProduct() != null) {
                Product product = journalFood.getProduct();
                double defaultWeightProduct = product.getWeight();
                double currentProductWeight = journalFood.getWeight();
                caloriesDTO.setName(product.getName());
                calories += currentProductWeight * product.getCalories() / defaultWeightProduct;
                proteins += currentProductWeight * product.getProteins() / defaultWeightProduct;
                fats += currentProductWeight * product.getFats() / defaultWeightProduct;
                carbohydrates += currentProductWeight * product.getCarbohydrates() / defaultWeightProduct;
                weight += currentProductWeight;
            }
            if (journalFood.getRecipe() != null) {
                Recipe recipe = journalFood.getRecipe();
                caloriesDTO.setName(recipe.getName());
                List<ComponentDish> componentDishes = recipe.getComponentDishes();
                for (ComponentDish componentDish : componentDishes) {
                    Product product = componentDish.getProduct();
                    double defaultWeightProduct = product.getWeight();
                    double currentProductWeight = componentDish.getWeightProduct();
                    calories += currentProductWeight * product.getCalories() / defaultWeightProduct;
                    proteins += currentProductWeight * product.getProteins() / defaultWeightProduct;
                    fats += currentProductWeight * product.getFats() / defaultWeightProduct;
                    carbohydrates += currentProductWeight * product.getCarbohydrates() / defaultWeightProduct;
                    weight += currentProductWeight;
                }
            }
            caloriesDTO.setCalories(calories);
            caloriesDTO.setProteins(proteins);
            caloriesDTO.setFats(fats);
            caloriesDTO.setCarbohydrates(carbohydrates);
            caloriesDTO.setWeight(weight);
        }
        return caloriesDTO;
    }

    public Double getCaloriesNorm(Profile profile){
        long age = ChronoUnit.YEARS.between(profile.getDateOfBirthday(), LocalDate.now());
        double calories=0;
        if (profile.getGender().equals(EGender.FEMALE)){
            calories =
                    (10* profile.getWeightFromWeightMeasurement())+(6.25* profile.getHeight())-(5*age)-161;
        }else {
            calories =
                    (10* profile.getWeightFromWeightMeasurement())+(6.25* profile.getHeight())-(5*age)+5;
        }
        if (profile.getLifestyle().equals(ELifestyle.SITTING)){
            calories*=1.2;
        }
        if (profile.getLifestyle().equals(ELifestyle.SEDENTARY)){
            calories*=1.375;
        }
        if (profile.getLifestyle().equals(ELifestyle.MOBILE)){
            calories*=1.7;
        }
        if (profile.getLifestyle().equals(ELifestyle.VERY_AGILE)){
            calories*=1.9;
        }
        return Math.ceil(calories);
    }

    public Double getCaloriesTarget(Profile profile){
        Double caloriesNorm = getCaloriesNorm(profile);

        if (profile.getTarget().equals(ETarget.WEIGHT_MAINTAINING)){
            return caloriesNorm;
        }
        if (profile.getTarget().equals(ETarget.WEIGHT_GAIN_025)){
            caloriesNorm+=caloriesNorm*0.15;
        }
        if (profile.getTarget().equals(ETarget.WEIGHT_GAIN_05)){
            caloriesNorm+=caloriesNorm*0.3;
        }
        if (profile.getTarget().equals(ETarget.WEIGHT_LOSS_05)){
            caloriesNorm-=caloriesNorm*0.2;
        }
        if (profile.getTarget().equals(ETarget.WEIGHT_LOSS_1)){
            caloriesNorm-=caloriesNorm*0.4;
        }

        return caloriesNorm;
    }


}
