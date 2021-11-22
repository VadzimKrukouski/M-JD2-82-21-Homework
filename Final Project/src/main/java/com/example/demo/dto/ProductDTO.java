package com.example.demo.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class ProductDTO {

    @NotEmpty (message = "Name should not be empty")
    private String name;

    private String brand;

    @Min(value = 0, message = "calories should be greater than 0")
    private double calories;

    @Min(value = 0, message = "proteins should be greater than 0")
    private double proteins;

    @Min(value = 0, message = "fats should be greater than 0")
    private double fats;

    @Min(value = 0, message = "carbohydrates should be greater than 0")
    private double carbohydrates;

    @Min(value = 0, message = "weight should be greater than 0")
    private double weight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
