package com.example.demo.model;

import com.example.demo.model.api.EMealTime;

import javax.persistence.*;

@Entity
@Table (name = "journal")
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Product product;

    @OneToOne
    private Dish dish;

    @Column
    private double weight;

    @Column
    private EMealTime mealTime;

    @OneToOne
    private Profile profile;

    public Journal() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public EMealTime getMealTime() {
        return mealTime;
    }

    public void setMealTime(EMealTime mealTime) {
        this.mealTime = mealTime;
    }
}
