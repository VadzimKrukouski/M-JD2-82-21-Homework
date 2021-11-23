package com.example.demo.dto;

public class CalculationCaloriesDTO {
    private String name;
    private double calories;
    private double proteins;
    private double fats;
    private double carbohydrates;
    private double weight;
    private double caloriesTarget;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public double getCaloriesTarget() {
        return caloriesTarget;
    }

    public void setCaloriesTarget(double caloriesTarget) {
        this.caloriesTarget = caloriesTarget;
    }
}
