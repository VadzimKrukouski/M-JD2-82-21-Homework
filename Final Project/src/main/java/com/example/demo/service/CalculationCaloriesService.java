package com.example.demo.service;

import com.example.demo.dto.CalculationCaloriesDTO;
import com.example.demo.model.ComponentDish;
import com.example.demo.model.JournalFood;
import com.example.demo.model.Product;
import com.example.demo.model.Recipe;
import com.example.demo.service.api.ICalculationCaloriesService;
import org.springframework.stereotype.Service;

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
}
